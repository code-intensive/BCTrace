package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InterlockKegActivity extends AppCompatActivity {
    String batch_string, mixer_string, sku_string, keg_string, time_string, date_string, operator_string, bin_string, operatorName, ip;
    String kegsArray_0, kegsArray_1, kegsArray_2, kegsArray_3, kegsArray_4, kegsArray_5, kegsArray_6, kegsArray_7, kegsArray_8, kegsArray_9, kegsArray_10, kegsArray_11;
    ProgressBar progressBar;

//    private static final String confirm_kegs = "http://192.168.2.219/PhP/interlockKeg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interlock_keg);

        progressBar = findViewById(R.id.progress_bar);
        ip = getResources().getString(R.string.ip);
        batch_string = getIntent().getExtras().getString("batch");
        mixer_string = getIntent().getExtras().getString("mixer");
        sku_string = getIntent().getExtras().getString("sku");
        keg_string = getIntent().getExtras().getString("keg");
        time_string = getIntent().getExtras().getString("time");
        date_string = getIntent().getExtras().getString("date");
        operator_string = getIntent().getExtras().getString("operator");
        bin_string = getIntent().getExtras().getString("bin");
        operatorName = getIntent().getExtras().getString("operatorsName");

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

        ConfirmKegs();

        Sprite wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void ConfirmKegs() {
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

        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/interlockKeg.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    switch (success) {
                        case "found_1": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_0_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_2": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_1_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_3": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_2_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_4": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_3_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_5": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_4_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_6": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_5_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_7": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_6_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_8": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_7_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_9": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_8_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_10": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_9_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_11": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_10_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "found_12": {
                            AlertDialog.Builder builder = new AlertDialog.Builder(InterlockKegActivity.this);
                            builder.setMessage(kegsArray_11_volley_string + " Already found and not consumed, please consume keg and re-generate batch");
                            builder.setTitle("Result");
                            builder.setNegativeButton("Ok", (dialog, which) -> finish());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        case "pass": {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(InterlockKegActivity.this, ResultActivity.class);
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
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InterlockKegActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
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
}