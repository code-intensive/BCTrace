package codeaggressive.com.bctrace;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class MinorActivity extends AppCompatActivity {

//    private static final String url = "http://192.168.2.219/PhP/minorResult.php";
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minor);
        ip = getResources().getString(R.string.ip);
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning...");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Result");
                builder.setPositiveButton("Scan Again", (dialog, which) -> scanCode()).setNegativeButton("Select Mixer", (dialog, which) -> mixers(result.getContents()));
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Toast.makeText(this, "No Result", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void mixers(String result) {
        final String[] mixers = {"Amixon", "Lodige", "Morton"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mixers);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(result);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Choices = mixers[which];

                Toast.makeText(MinorActivity.this, Choices, Toast.LENGTH_LONG).show();

                switch (Choices) {
                    case "Amixon": {
                        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/minorResult.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MinorActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MinorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<String, String>();
                                param.put("minorNo", result);
                                param.put("mixer", Choices);
                                param.put("amixon", "amixon");
                                return param;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(request);
                        finish();

                        break;
                    }
                    case "Lodige": {
                        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/minorResult.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MinorActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MinorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<String, String>();
                                param.put("minorNo", result);
                                param.put("mixer", Choices);
                                param.put("lodige", "lodige");
                                return param;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(request);
                        finish();
                        break;
                    }
                    case "Morton": {
                        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/minorResult.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MinorActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MinorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<String, String>();
                                param.put("minorNo", result);
                                param.put("mixer", Choices);
                                param.put("morton", "morton");
                                return param;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(request);
                        finish();

                        break;
                    }
                }

                StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/minorResult.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MinorActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MinorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("minorNo", result);
                        param.put("mixer", Choices);
                        return param;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
                finish();
            }
        });
        final AlertDialog a = builder.create();
        a.show();
    }
}