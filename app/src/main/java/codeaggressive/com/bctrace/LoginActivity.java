package codeaggressive.com.bctrace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button submit;
    EditText username, password;
    ProgressBar progressBar;
    String ip;

//    private static final String login_url = "http://192.168.2.219/PhP/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_text);
        password = findViewById(R.id.password_text);
        submit = findViewById(R.id.login);
        progressBar = findViewById(R.id.progress_bar);
        ip = getResources().getString(R.string.ip);

        Sprite wave = new Wave();
        progressBar.setVisibility(View.GONE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setIndeterminateDrawable(wave);
                progressBar.setVisibility(View.VISIBLE);
                String username_text = username.getText().toString();
                String password_text = password.getText().toString();
                if (username_text.isEmpty() || password_text.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Fill empty field", Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/login.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Failed")) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray results = jsonObject.getJSONArray("result");
                                    JSONObject jo = results.getJSONObject(0);
                                    String OperatorName = jo.getString("operatorName");
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("operatorsName", OperatorName);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, OperatorName, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> param = new HashMap<String, String>();
                            param.put("username", username_text);
                            param.put("password", password_text);
                            return param;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(request);
                }
            }
        });
    }
}