package codeaggressive.com.bctrace;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class KegScanActivity extends AppCompatActivity {
    String batch_string, mixer_string, sku_string, keg_string, time_string, date_string, operator_string, bin_string, operatorName;
    String kegsArray_0, kegsArray_1, kegsArray_2, kegsArray_3, kegsArray_4, kegsArray_5, kegsArray_6, kegsArray_7, kegsArray_8, kegsArray_9, kegsArray_10, kegsArray_11;
    ArrayList<String> kegs_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keg_scan);
        kegs_list = new ArrayList<>();
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Kegs...");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                if(kegs_list.contains(result.getContents())){
                    Toast.makeText(this, "Keg Already scanned", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(result.getContents());
                    builder.setTitle("Result");
                    builder.setPositiveButton("Next", (dialog, which) -> scanCode()).setNegativeButton("finish", (dialog, which) -> sendResult());
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    kegs_list.add(result.getContents());
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(result.getContents());
                    builder.setTitle("Result");
                    builder.setPositiveButton("Next", (dialog, which) -> scanCode()).setNegativeButton("finish", (dialog, which) -> sendResult());
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            } else {
                Toast.makeText(this, "No Result", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendResult() {
        batch_string = getIntent().getExtras().getString("batch");
        mixer_string = getIntent().getExtras().getString("mixer");
        sku_string = getIntent().getExtras().getString("sku");
        keg_string = String.valueOf(kegs_list.size());
        time_string = getIntent().getExtras().getString("time");
        date_string = getIntent().getExtras().getString("date");
        operator_string = getIntent().getExtras().getString("operator");
        bin_string = getIntent().getExtras().getString("bin");
        operatorName = getIntent().getExtras().getString("operatorsName");

        for (int i = 0; i < kegs_list.size(); i++) {
            try {
                kegsArray_0 = kegs_list.get(0);
                kegsArray_1 = kegs_list.get(1);
                kegsArray_2 = kegs_list.get(2);
                kegsArray_3 = kegs_list.get(3);
                kegsArray_4 = kegs_list.get(4);
                kegsArray_5 = kegs_list.get(5);
                kegsArray_6 = kegs_list.get(6);
                kegsArray_7 = kegs_list.get(7);
                kegsArray_8 = kegs_list.get(8);
                kegsArray_9 = kegs_list.get(9);
                kegsArray_10 = kegs_list.get(10);
                kegsArray_11 = kegs_list.get(11);
            } catch (Exception ignored) {
            }
        }

        Intent intent = new Intent(KegScanActivity.this, InterlockKegActivity.class);

        intent.putExtra("batch", batch_string);
        intent.putExtra("mixer", mixer_string);
        intent.putExtra("sku", sku_string);
        intent.putExtra("keg", keg_string);
        intent.putExtra("time", time_string);
        intent.putExtra("date", date_string);
        intent.putExtra("operator", operator_string);
        intent.putExtra("bin", bin_string);
        intent.putExtra("kegsArray_0", kegsArray_0);
        intent.putExtra("kegsArray_1", kegsArray_1);
        intent.putExtra("kegsArray_2", kegsArray_2);
        intent.putExtra("kegsArray_3", kegsArray_3);
        intent.putExtra("kegsArray_4", kegsArray_4);
        intent.putExtra("kegsArray_5", kegsArray_5);
        intent.putExtra("kegsArray_6", kegsArray_6);
        intent.putExtra("kegsArray_7", kegsArray_7);
        intent.putExtra("kegsArray_8", kegsArray_8);
        intent.putExtra("kegsArray_9", kegsArray_9);
        intent.putExtra("kegsArray_10", kegsArray_10);
        intent.putExtra("kegsArray_11", kegsArray_11);
        intent.putExtra("operatorsName", operatorName);
        startActivity(intent);
        finish();
    }
}