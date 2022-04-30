package codeaggressive.com.bctrace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class
RefactorActivity extends AppCompatActivity {
    Button setButton, resetButton;
    String set_string = "barricade";
    private final String setunset_url = "https://bctrace.000webhostapp.com/BCTrace/setunset.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refactor);

        setButton = findViewById(R.id.set);
        resetButton = findViewById(R.id.reset);

        setButton.setOnClickListener(v -> setCommand());

        resetButton.setOnClickListener(v -> resetCommand());
    }

    private void setCommand() {
        StringRequest request = new StringRequest(Request.Method.POST, setunset_url, response -> Toast.makeText(RefactorActivity.this, response, Toast.LENGTH_SHORT).show(), error -> Toast.makeText(RefactorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("set", set_string);
                param.put("cmd", "setRefactor");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }

    private void resetCommand() {
        StringRequest request = new StringRequest(Request.Method.POST, setunset_url, response -> Toast.makeText(RefactorActivity.this, response, Toast.LENGTH_SHORT).show(), error -> Toast.makeText(RefactorActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("cmd", "unsetRefactor");
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}