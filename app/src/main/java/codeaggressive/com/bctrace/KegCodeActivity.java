package codeaggressive.com.bctrace;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Set;
import java.util.UUID;

import Helper.BitmapHelper;

public class KegCodeActivity extends AppCompatActivity {
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    private static OutputStream outputStream;
    BluetoothDevice mBluetoothDevice;
    Button printButton, shareButton, submitKegNo;
    ImageView kegImage;
    EditText kegInput;
    TextView pairedevice;

    int mWidth;
    int mHeight;
    BitSet dots;
    String mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kegcode);

        shareButton = findViewById(R.id.shareButton);
        kegInput = findViewById(R.id.KegInput);
        submitKegNo = findViewById(R.id.submitKegNo);
        kegImage = findViewById(R.id.kegImage);
        printButton = findViewById(R.id.printButton);
        pairedevice = findViewById(R.id.pairedDevice);
        dots = new BitSet();

        pairedevice.setText("Connect");

        submitKegNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kegNoString = kegInput.getText().toString();

                if (kegNoString.isEmpty()) {
                    Toast.makeText(KegCodeActivity.this, "Empty Text Field", Toast.LENGTH_SHORT).show();
                } else {
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {
                        BitMatrix matrix = writer.encode(kegNoString, BarcodeFormat.CODE_128, 350, 350);

                        BarcodeEncoder encoder = new BarcodeEncoder();

                        Bitmap bitmap = encoder.createBitmap(matrix);


                        BitmapHelper.getInstance().setBitmap(bitmap);

                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                        manager.hideSoftInputFromWindow(kegInput.getApplicationWindowToken(), 0);

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        BitmapHelper.getInstance().getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

                        if (BitmapHelper.getInstance().getBitmap() == null) {
                            Toast.makeText(KegCodeActivity.this, "Bitmap Null", Toast.LENGTH_SHORT).show();
                        } else {
                            kegImage.setImageBitmap(BitmapHelper.getInstance().getBitmap());
                        }
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        shareButton.setOnClickListener(v -> shareImage());

        printButton.setOnClickListener(v -> {
            Thread t = new Thread() {
                public void run() {
                    try {
                        if (mBluetoothSocket != null) {
                            OutputStream os = mBluetoothSocket.getOutputStream();
                            Bitmap image = BitmapHelper.getInstance().getBitmap();
                            if (image != null) {
                                String printable = " ";
                                String CodeText = kegInput.getText().toString();
                                String header = "Unilever" + " " + "Nig" + " ----- " + "(" + CodeText + ")\n";
                                printable = printable + header;
                                os.write(PrinterCommands.ESC_ALIGN_CENTER);
                                os.write(printable.getBytes());
                                os.write(PrinterCommands.SET_LINE_SPACING_30);
                                convertBitmap(image);
                                int offset = 0;
                                while (offset < image.getHeight()) {
                                    os.write(PrinterCommands.SELECT_BIT_IMAGE_MODE);
                                    for (int x = 0; x < image.getWidth(); ++x) {

                                        for (int k = 0; k < 3; ++k) {

                                            byte slice = 0;
                                            for (int b = 0; b < 8; ++b) {
                                                int y = (((offset / 8) + k) * 8) + b;
                                                int i = (y * image.getWidth()) + x;
                                                boolean v = false;
                                                if (i < dots.length()) {
                                                    v = dots.get(i);
                                                }
                                                slice |= (byte) ((v ? 1 : 0) << (7 - b));
                                            }
                                            os.write(slice);
                                        }
                                    }
                                    offset += 24;
                                    os.write(PrinterCommands.FEED_LINE);
                                }
                            }
                            os.write(PrinterCommands.SET_LINE_SPACING_30);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        });

        pairedevice.setOnClickListener(v ->

        {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(KegCodeActivity.this, "Message1", Toast.LENGTH_SHORT).show();
            } else {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent,
                            REQUEST_ENABLE_BT);
                } else {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(KegCodeActivity.this, DeviceList.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                }
            }
        });

    }

    public String convertBitmap(Bitmap inputBitmap) {
        if (inputBitmap != null) {

            mWidth = inputBitmap.getWidth();
            mHeight = inputBitmap.getHeight();

            convertArgbToGrayscale(inputBitmap, mWidth, mHeight);
            mStatus = "ok";
        }
        return mStatus;


    }

    private void convertArgbToGrayscale(Bitmap bmpOriginal, int width, int height) {
        int pixel;
        int k = 0;
        int B = 0, G = 0, R = 0;
        dots = new BitSet();
        try {

            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    // get one pixel color
                    pixel = bmpOriginal.getPixel(y, x);

                    // retrieve color of all channels
                    R = Color.red(pixel);
                    G = Color.green(pixel);
                    B = Color.blue(pixel);
                    // take conversion up to one single value by calculating
                    // pixel intensity.
                    R = (int) (0.299 * R + 0.587 * G + 0.114 * B);
                    // set bit into bitset, by calculating the pixel's luma
                    if (R < 55) {
                        dots.set(k);//this is the bitset that i'm printing
                    }
                    k++;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            Log.e(TAG, e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onActivityResult(int mRequestCode, int mResultCode, Intent mDataIntent) {
        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);

        switch (mRequestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (mResultCode == Activity.RESULT_OK) {
                    Bundle mExtra = mDataIntent.getExtras();
                    String mDeviceAddress = mExtra.getString("DeviceAddress");
                    Log.v(TAG, "Coming incoming address " + mDeviceAddress);
                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(mDeviceAddress);
                    mBluetoothConnectProgressDialog = ProgressDialog.show(this, "Connecting...", mBluetoothDevice.getName() + " : " + mBluetoothDevice.getAddress(), true, false);
                    Thread mBlutoothConnectThread = new Thread(this::run);
                    mBlutoothConnectThread.start();
                }
                break;

            case REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(KegCodeActivity.this, DeviceList.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(KegCodeActivity.this, "Message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  " + mDevice.getAddress());
            }
        }
    }

    public void run() {
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            mHandler.sendEmptyMessage(0);
        } catch (IOException eConnectException) {
            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    private void closeSocket(BluetoothSocket nOpenSocket) {
        try {
            nOpenSocket.close();
            Log.d(TAG, "SocketClosed");
        } catch (IOException ex) {
            Log.d(TAG, "CouldNotCloseSocket");
        }
    }

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            mBluetoothConnectProgressDialog.dismiss();
            Toast.makeText(KegCodeActivity.this, "DeviceConnected", Toast.LENGTH_SHORT).show();
        }
    };

    public static byte intToByteArray(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();

        for (int k = 0; k < b.length; k++) {
            System.out.println("Selva  [" + k + "] = " + "0x" + UnicodeFormatter.byteToHex(b[k]));
        }
        return b[3];
    }

    public byte[] sel(int val) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putInt(val);
        buffer.flip();
        return buffer.array();
    }

    private void shareImage() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Bitmap image = BitmapHelper.getInstance().getBitmap();
        File file = new File(getExternalCacheDir() + "/" + "Barcode" + ".png");
        Intent intent;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent, "Share Image Via: "));
    }

}