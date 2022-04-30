package codeaggressive.com.bctrace;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.ImagePrintable;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Helper.BitmapHelper;

public class ResultActivity extends AppCompatActivity implements PrintingCallback {
    TextView batchRe, mixerRe, skuRe, kegRe, timeRe, dateRe, operatorRe, binRe;
    Button printBtn, shareBtn, backBtn;
    ImageView barimage;
    String batch_string, mixer_string, sku_string, keg_string, time_string, date_string, operator_string, bin_string, ip;
    String kegsArray_0, kegsArray_1, kegsArray_2, kegsArray_3, kegsArray_4, kegsArray_5, kegsArray_6, kegsArray_7, kegsArray_8, kegsArray_9, kegsArray_10, kegsArray_11;
    Printing printing;
    DatabaseHelper myDB;
    ArrayList<String> bracodeList;

//    private static final String url = "http://192.168.2.219/PhP/batch.php";
//    private static final String kegs = "http://192.168.2.219/PhP/kegs.php";
//    private static final String find = "http://192.168.2.219/PhP/find.php";

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ip = getResources().getString(R.string.ip);
        batchRe = findViewById(R.id.batch_No_Result);
        mixerRe = findViewById(R.id.mixer_Result);
        skuRe = findViewById(R.id.sku_Result);
        kegRe = findViewById(R.id.kegNo_Result);
        timeRe = findViewById(R.id.time_Result);
        dateRe = findViewById(R.id.date_Result);
        operatorRe = findViewById(R.id.operator_Result);
        binRe = findViewById(R.id.bin_Result);
        myDB = new DatabaseHelper(this);
        bracodeList = new ArrayList<>();

        backBtn = findViewById(R.id.back_btn);
        printBtn = findViewById(R.id.print_btn);
        shareBtn = findViewById(R.id.share_btn);

        barimage = findViewById(R.id.barcode_image);

        batch_string = getIntent().getExtras().getString("batch");
        mixer_string = getIntent().getExtras().getString("mixer");
        sku_string = getIntent().getExtras().getString("sku");
        keg_string = getIntent().getExtras().getString("keg");
        time_string = getIntent().getExtras().getString("time");
        date_string = getIntent().getExtras().getString("date");
        operator_string = getIntent().getExtras().getString("operator");
        bin_string = getIntent().getExtras().getString("bin");

        kegsArray_0 = getIntent().getExtras().getString("kegsArray_0");
        kegsArray_1 = getIntent().getExtras().getString("kegsArray_1");
        kegsArray_2 = getIntent().getExtras().getString("kegsArray_2");
        kegsArray_3 = getIntent().getExtras().getString("kegsArray_3");
        kegsArray_4 = getIntent().getExtras().getString("kegsArray_4");
        kegsArray_5 = getIntent().getExtras().getString("kegsArray_5");
        kegsArray_6 = getIntent().getExtras().getString("kegsArray_6");
        kegsArray_7 = getIntent().getExtras().getString("kegsArray_7");
        kegsArray_8 = getIntent().getExtras().getString("kegsArray_8");
        kegsArray_9 = getIntent().getExtras().getString("kegsArray_9");
        kegsArray_10 = getIntent().getExtras().getString("kegsArray_10");
        kegsArray_11 = getIntent().getExtras().getString("kegsArray_11");

        batchRe.setText(batch_string);
        mixerRe.setText(mixer_string);
        skuRe.setText(sku_string);
        kegRe.setText(keg_string);
        timeRe.setText(time_string);
        dateRe.setText(date_string);
        operatorRe.setText(operator_string);
        binRe.setText(bin_string);


        bracodeList.add(batch_string);
        bracodeList.add(mixer_string);
        bracodeList.add(sku_string);
        bracodeList.add(keg_string);
        bracodeList.add(time_string);
        bracodeList.add(date_string);
        bracodeList.add(operator_string);
        bracodeList.add(bin_string);
        bracodeList.add(kegsArray_0);
        bracodeList.add(kegsArray_1);
        bracodeList.add(kegsArray_2);
        bracodeList.add(kegsArray_3);
        bracodeList.add(kegsArray_4);
        bracodeList.add(kegsArray_5);
        bracodeList.add(kegsArray_6);
        bracodeList.add(kegsArray_7);
        bracodeList.add(kegsArray_8);
        bracodeList.add(kegsArray_9);
        bracodeList.add(kegsArray_10);
        bracodeList.add(kegsArray_11);

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(String.valueOf(bracodeList), BarcodeFormat.QR_CODE, 350, 350);

            BarcodeEncoder encoder = new BarcodeEncoder();

            Bitmap bitmap = encoder.createBitmap(matrix);

            BitmapHelper.getInstance().setBitmap(bitmap);

            String batch_sql_string = batch_string;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BitmapHelper.getInstance().getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            boolean insertImage = myDB.insertGenerateImage(batch_sql_string, imageInByte);
            if (insertImage) {
            } else {
                Toast.makeText(getApplicationContext(), "failed to Insert Image", Toast.LENGTH_SHORT).show();
            }

            if (BitmapHelper.getInstance().getBitmap() == null) {
                Toast.makeText(ResultActivity.this, "Bitmap Null", Toast.LENGTH_SHORT).show();
            } else {
                barimage.setImageBitmap(BitmapHelper.getInstance().getBitmap());
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        InsertSql();
        InsertDatabase();

        shareBtn.setOnClickListener(v -> {
            shareImage();
        });

        backBtn.setOnClickListener(v -> {
            String operatorName = getIntent().getExtras().getString("operatorsName");
            Intent intent = new Intent(ResultActivity.this, GenerateActivity.class);
            intent.putExtra("operatorsName", operatorName);
            startActivity(intent);
            finish();
        });

        if (printing != null) {
            printing.setPrintingCallback(this);
        }

        printBtn.setOnClickListener(v -> {
            if (!Printooth.INSTANCE.hasPairedPrinter()) {
                startActivityForResult(new Intent(ResultActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
            } else {
                print();
            }
        });
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

    private void print() {
        ArrayList<Printable> printables = new ArrayList<>();
        Bitmap image = BitmapHelper.getInstance().getBitmap();
        printables.add(new ImagePrintable.Builder(image).build());
        printing.print(printables);
    }

    @Override
    public void connectingWithPrinter() {
        Toast.makeText(this, "Connecting to printer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionFailed(String s) {
        Toast.makeText(this, "Failed to Connect", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printingOrderSentSuccessfully() {
        Toast.makeText(this, "Sent Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK) {
            initPrinting();
        }
    }

    private void initPrinting() {
        if (!Printooth.INSTANCE.hasPairedPrinter()) {
            printing = Printooth.INSTANCE.printer();
        }
        if (printing != null) {
            printing.setPrintingCallback(this);
        }
    }

    //------------------------------------//
    private void InsertDatabase() {
        String batch_volley_string = batch_string;
        String mixer_volley_string = mixer_string;
        String sku_volley_string = sku_string;
        String keg_volley_string = keg_string;
        String time_volley_string = time_string;
        String date_volley_string = date_string;
        String operator_volley_string = operator_string;
        String bin_volley_string = bin_string;

        String Arraykegs_0;
        String Arraykegs_1;
        String Arraykegs_2;
        String Arraykegs_3;
        String Arraykegs_4;
        String Arraykegs_5;
        String Arraykegs_6;
        String Arraykegs_7;
        String Arraykegs_8;
        String Arraykegs_9;
        String Arraykegs_10;
        String Arraykegs_11;

        if (kegsArray_0 == null) {
            Arraykegs_0 = kegsArray_0 = "Empty";
        } else {
            Arraykegs_0 = kegsArray_0;
        }

        if (kegsArray_1 == null) {
            Arraykegs_1 = kegsArray_1 = "Empty";
        } else {
            Arraykegs_1 = kegsArray_1;
        }

        if (kegsArray_2 == null) {
            Arraykegs_2 = kegsArray_2 = "Empty";
        } else {
            Arraykegs_2 = kegsArray_2;
        }

        if (kegsArray_3 == null) {
            Arraykegs_3 = kegsArray_3 = "Empty";
        } else {
            Arraykegs_3 = kegsArray_3;
        }

        if (kegsArray_4 == null) {
            Arraykegs_4 = kegsArray_4 = "Empty";
        } else {
            Arraykegs_4 = kegsArray_4;
        }

        if (kegsArray_5 == null) {
            Arraykegs_5 = kegsArray_5 = "Empty";
        } else {
            Arraykegs_5 = kegsArray_5;
        }

        if (kegsArray_6 == null) {
            Arraykegs_6 = kegsArray_6 = "Empty";
        } else {
            Arraykegs_6 = kegsArray_6;
        }

        if (kegsArray_7 == null) {
            Arraykegs_7 = kegsArray_7 = "Empty";
        } else {
            Arraykegs_7 = kegsArray_7;
        }

        if (kegsArray_8 == null) {
            Arraykegs_8 = kegsArray_8 = "Empty";
        } else {
            Arraykegs_8 = kegsArray_8;
        }

        if (kegsArray_9 == null) {
            Arraykegs_9 = kegsArray_9 = "Empty";
        } else {
            Arraykegs_9 = kegsArray_9;
        }

        if (kegsArray_10 == null) {
            Arraykegs_10 = kegsArray_10 = "Empty";
        } else {
            Arraykegs_10 = kegsArray_10;
        }

        if (kegsArray_11 == null) {
            Arraykegs_11 = kegsArray_11 = "Empty";
        } else {
            Arraykegs_11 = kegsArray_11;
        }

        String kegsArray_0_volley_string = Arraykegs_0;
        String kegsArray_1_volley_string = Arraykegs_1;
        String kegsArray_2_volley_string = Arraykegs_2;
        String kegsArray_3_volley_string = Arraykegs_3;
        String kegsArray_4_volley_string = Arraykegs_4;
        String kegsArray_5_volley_string = Arraykegs_5;
        String kegsArray_6_volley_string = Arraykegs_6;
        String kegsArray_7_volley_string = Arraykegs_7;
        String kegsArray_8_volley_string = Arraykegs_8;
        String kegsArray_9_volley_string = Arraykegs_9;
        String kegsArray_10_volley_string = Arraykegs_10;
        String kegsArray_11_volley_string = Arraykegs_11;

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/batch.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("found")) {
                        Toast.makeText(ResultActivity.this, "Failed: Data Already Inserted", Toast.LENGTH_SHORT).show();
                    } else if (success.equals("successful")) {
                        InsertToExcel();
                        InsertKegs();
                        FindDatabase();
                        Toast.makeText(ResultActivity.this, "Successful...", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResultActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", batch_volley_string);
                param.put("mixer", mixer_volley_string);
                param.put("sku", sku_volley_string);
                param.put("kegNo", keg_volley_string);
                param.put("edate", date_volley_string);
                param.put("etime", time_volley_string);
                param.put("operator", operator_volley_string);
                param.put("binNo", bin_volley_string);
                param.put("kegsArray_0", kegsArray_0_volley_string);
                param.put("kegsArray_1", kegsArray_1_volley_string);
                param.put("kegsArray_2", kegsArray_2_volley_string);
                param.put("kegsArray_3", kegsArray_3_volley_string);
                param.put("kegsArray_4", kegsArray_4_volley_string);
                param.put("kegsArray_5", kegsArray_5_volley_string);
                param.put("kegsArray_6", kegsArray_6_volley_string);
                param.put("kegsArray_7", kegsArray_7_volley_string);
                param.put("kegsArray_8", kegsArray_8_volley_string);
                param.put("kegsArray_9", kegsArray_9_volley_string);
                param.put("kegsArray_10", kegsArray_10_volley_string);
                param.put("kegsArray_11", kegsArray_11_volley_string);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void InsertKegs() {
        String batch_volley_string = batch_string;
        String Arraykegs_0;
        String Arraykegs_1;
        String Arraykegs_2;
        String Arraykegs_3;
        String Arraykegs_4;
        String Arraykegs_5;
        String Arraykegs_6;
        String Arraykegs_7;
        String Arraykegs_8;
        String Arraykegs_9;
        String Arraykegs_10;
        String Arraykegs_11;

        if (kegsArray_0 == null) {
            Arraykegs_0 = kegsArray_0 = "Empty";
        } else {
            Arraykegs_0 = kegsArray_0;
        }

        if (kegsArray_1 == null) {
            Arraykegs_1 = kegsArray_1 = "Empty";
        } else {
            Arraykegs_1 = kegsArray_1;
        }

        if (kegsArray_2 == null) {
            Arraykegs_2 = kegsArray_2 = "Empty";
        } else {
            Arraykegs_2 = kegsArray_2;
        }

        if (kegsArray_3 == null) {
            Arraykegs_3 = kegsArray_3 = "Empty";
        } else {
            Arraykegs_3 = kegsArray_3;
        }

        if (kegsArray_4 == null) {
            Arraykegs_4 = kegsArray_4 = "Empty";
        } else {
            Arraykegs_4 = kegsArray_4;
        }

        if (kegsArray_5 == null) {
            Arraykegs_5 = kegsArray_5 = "Empty";
        } else {
            Arraykegs_5 = kegsArray_5;
        }

        if (kegsArray_6 == null) {
            Arraykegs_6 = kegsArray_6 = "Empty";
        } else {
            Arraykegs_6 = kegsArray_6;
        }

        if (kegsArray_7 == null) {
            Arraykegs_7 = kegsArray_7 = "Empty";
        } else {
            Arraykegs_7 = kegsArray_7;
        }

        if (kegsArray_8 == null) {
            Arraykegs_8 = kegsArray_8 = "Empty";
        } else {
            Arraykegs_8 = kegsArray_8;
        }

        if (kegsArray_9 == null) {
            Arraykegs_9 = kegsArray_9 = "Empty";
        } else {
            Arraykegs_9 = kegsArray_9;
        }

        if (kegsArray_10 == null) {
            Arraykegs_10 = kegsArray_10 = "Empty";
        } else {
            Arraykegs_10 = kegsArray_10;
        }

        if (kegsArray_11 == null) {
            Arraykegs_11 = kegsArray_11 = "Empty";
        } else {
            Arraykegs_11 = kegsArray_11;
        }

        String kegsArray_0_volley_string = Arraykegs_0;
        String kegsArray_1_volley_string = Arraykegs_1;
        String kegsArray_2_volley_string = Arraykegs_2;
        String kegsArray_3_volley_string = Arraykegs_3;
        String kegsArray_4_volley_string = Arraykegs_4;
        String kegsArray_5_volley_string = Arraykegs_5;
        String kegsArray_6_volley_string = Arraykegs_6;
        String kegsArray_7_volley_string = Arraykegs_7;
        String kegsArray_8_volley_string = Arraykegs_8;
        String kegsArray_9_volley_string = Arraykegs_9;
        String kegsArray_10_volley_string = Arraykegs_10;
        String kegsArray_11_volley_string = Arraykegs_11;

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/kegs.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("found")) {
                    } else {
                        Toast.makeText(ResultActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResultActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", batch_volley_string);
                param.put("kegsArray_0", kegsArray_0_volley_string);
                param.put("kegsArray_1", kegsArray_1_volley_string);
                param.put("kegsArray_2", kegsArray_2_volley_string);
                param.put("kegsArray_3", kegsArray_3_volley_string);
                param.put("kegsArray_4", kegsArray_4_volley_string);
                param.put("kegsArray_5", kegsArray_5_volley_string);
                param.put("kegsArray_6", kegsArray_6_volley_string);
                param.put("kegsArray_7", kegsArray_7_volley_string);
                param.put("kegsArray_8", kegsArray_8_volley_string);
                param.put("kegsArray_9", kegsArray_9_volley_string);
                param.put("kegsArray_10", kegsArray_10_volley_string);
                param.put("kegsArray_11", kegsArray_11_volley_string);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void InsertSql() {
        String Arraykegs_0;
        String Arraykegs_1;
        String Arraykegs_2;
        String Arraykegs_3;
        String Arraykegs_4;
        String Arraykegs_5;
        String Arraykegs_6;
        String Arraykegs_7;
        String Arraykegs_8;
        String Arraykegs_9;
        String Arraykegs_10;
        String Arraykegs_11;

        if (kegsArray_0 == null) {
            Arraykegs_0 = kegsArray_0 = "Empty";
        } else {
            Arraykegs_0 = kegsArray_0;
        }

        if (kegsArray_1 == null) {
            Arraykegs_1 = kegsArray_1 = "Empty";
        } else {
            Arraykegs_1 = kegsArray_1;
        }

        if (kegsArray_2 == null) {
            Arraykegs_2 = kegsArray_2 = "Empty";
        } else {
            Arraykegs_2 = kegsArray_2;
        }

        if (kegsArray_3 == null) {
            Arraykegs_3 = kegsArray_3 = "Empty";
        } else {
            Arraykegs_3 = kegsArray_3;
        }

        if (kegsArray_4 == null) {
            Arraykegs_4 = kegsArray_4 = "Empty";
        } else {
            Arraykegs_4 = kegsArray_4;
        }

        if (kegsArray_5 == null) {
            Arraykegs_5 = kegsArray_5 = "Empty";
        } else {
            Arraykegs_5 = kegsArray_5;
        }

        if (kegsArray_6 == null) {
            Arraykegs_6 = kegsArray_6 = "Empty";
        } else {
            Arraykegs_6 = kegsArray_6;
        }

        if (kegsArray_7 == null) {
            Arraykegs_7 = kegsArray_7 = "Empty";
        } else {
            Arraykegs_7 = kegsArray_7;
        }

        if (kegsArray_8 == null) {
            Arraykegs_8 = kegsArray_8 = "Empty";
        } else {
            Arraykegs_8 = kegsArray_8;
        }

        if (kegsArray_9 == null) {
            Arraykegs_9 = kegsArray_9 = "Empty";
        } else {
            Arraykegs_9 = kegsArray_9;
        }

        if (kegsArray_10 == null) {
            Arraykegs_10 = kegsArray_10 = "Empty";
        } else {
            Arraykegs_10 = kegsArray_10;
        }

        if (kegsArray_11 == null) {
            Arraykegs_11 = kegsArray_11 = "Empty";
        } else {
            Arraykegs_11 = kegsArray_11;
        }
        String batch_sql_string = batch_string;
        String mixer_sql_string = mixer_string;
        String sku_sql_string = sku_string;
        String keg_sql_string = keg_string;
        String time_sql_string = time_string;
        String date_sql_string = date_string;
        String operator_sql_string = operator_string;
        String bin_sql_string = bin_string;

        String keg_1_string = Arraykegs_0;
        String keg_2_string = Arraykegs_1;
        String keg_3_string = Arraykegs_2;
        String keg_4_string = Arraykegs_3;
        String keg_5_string = Arraykegs_4;
        String keg_6_string = Arraykegs_5;
        String keg_7_string = Arraykegs_6;
        String keg_8_string = Arraykegs_7;
        String keg_9_string = Arraykegs_8;
        String keg_10_string = Arraykegs_9;
        String keg_11_string = Arraykegs_10;
        String keg_12_string = Arraykegs_11;

        boolean isInserted = myDB.insertGenerateData(batch_sql_string, mixer_sql_string, sku_sql_string, keg_sql_string, date_sql_string, time_sql_string, operator_sql_string, bin_sql_string, keg_1_string, keg_2_string, keg_3_string, keg_4_string, keg_5_string, keg_6_string, keg_7_string, keg_8_string, keg_9_string, keg_10_string, keg_11_string, keg_12_string);
        if (isInserted) {
            Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_LONG).show();
        }
    }

    private void InsertToExcel() {
        File sources = new File(Environment.getExternalStorageDirectory() + "/Generate.xlsx");
        try {
            FileInputStream existing = new FileInputStream(sources);
            XSSFWorkbook workbook = new XSSFWorkbook(existing);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            int lastRow = worksheet.getLastRowNum();
            XSSFRow row = worksheet.createRow(++lastRow);
            row.createCell(0).setCellValue(batch_string);
            row.createCell(1).setCellValue(mixer_string);
            row.createCell(2).setCellValue(sku_string);
            row.createCell(3).setCellValue(keg_string);
            row.createCell(5).setCellValue(date_string);
            row.createCell(4).setCellValue(time_string);
            row.createCell(6).setCellValue(operator_string);
            row.createCell(7).setCellValue(bin_string);
            row.createCell(8).setCellValue(kegsArray_0);
            row.createCell(9).setCellValue(kegsArray_1);
            row.createCell(10).setCellValue(kegsArray_2);
            row.createCell(11).setCellValue(kegsArray_3);
            row.createCell(12).setCellValue(kegsArray_4);
            row.createCell(13).setCellValue(kegsArray_5);
            row.createCell(14).setCellValue(kegsArray_6);
            row.createCell(15).setCellValue(kegsArray_7);
            row.createCell(16).setCellValue(kegsArray_8);
            row.createCell(17).setCellValue(kegsArray_9);
            row.createCell(18).setCellValue(kegsArray_10);
            row.createCell(19).setCellValue(kegsArray_11);
            existing.close();
            FileOutputStream newinput = new FileOutputStream(sources);
            workbook.write(newinput);
            newinput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void FindDatabase() {
        String batch_volley_string = batch_string;
        String mixer_volley_string = mixer_string;
        String sku_volley_string = sku_string;
        String keg_volley_string = keg_string;
        String time_volley_string = time_string;
        String date_volley_string = date_string;
        String operator_volley_string = operator_string;
        String bin_volley_string = bin_string;

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/find.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("found")) {
                        Toast.makeText(ResultActivity.this, "Failed: Data Already Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResultActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResultActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("batchNo", batch_volley_string);
                param.put("mixer", mixer_volley_string);
                param.put("sku", sku_volley_string);
                param.put("kegNo", keg_volley_string);
                param.put("edate", date_volley_string);
                param.put("etime", time_volley_string);
                param.put("operator", operator_volley_string);
                param.put("binNo", bin_volley_string);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}