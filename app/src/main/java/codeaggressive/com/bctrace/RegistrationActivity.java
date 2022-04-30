package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText operator, username, password, confirm_password;
    Button register;

//    private final String register_url = "http://192.168.2.219/PhP/registration.php";
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ip = getResources().getString(R.string.ip);
        operator = findViewById(R.id.operator_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.passText);
        confirm_password = findViewById(R.id.confirm_PassText);
        register = findViewById(R.id.registration_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
    }

    private void registration() {
        String operator_string = operator.getText().toString();
        String username_string = username.getText().toString();
        String password_string = password.getText().toString();
        String confirm_password_string = confirm_password.getText().toString();

        if (operator_string.equals("") || username_string.equals("") || password_string.equals("") || confirm_password_string.equals("")){
            Toast.makeText(this, "All Fields Require", Toast.LENGTH_SHORT).show();
            password.setText("");
            confirm_password.setText("");
        }
        else {

            if (password_string.equals(confirm_password_string)) {
                StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/registration.php", response -> Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show(), new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrationActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("username", username_string);
                        param.put("password", password_string);
                        param.put("operator_name", operator_string);
                        return param;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            } else {
                Toast.makeText(this, "Password not Matched", Toast.LENGTH_SHORT).show();
            }
        }
    }
}