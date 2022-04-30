package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class interlockingActivity extends AppCompatActivity {
    Button enter_button;
    TextView description_text;
    String ip;

//    private static final String interlock_url = "http://192.168.2.219/PhP/interlock.php";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interlocking);
        description_text = findViewById(R.id.description_Text);
        enter_button = findViewById(R.id.enter);
        ip = getResources().getString(R.string.ip);
        description_text.setText("Loading...");
        String description = "The previous scanned Minor Data has not been Generated\nPlease generate previous data to scan another\nor click on ENTER to scan a new data ";
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip+"/PhP/interlock.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("found")) {
                        description_text.setText("Processing...\n\nplease wait");
                        Intent intent = new Intent(getApplicationContext(), MinorActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else if (success.equals("not found")){
                        description_text.setText(description);
                    }
                    else{
                        Toast.makeText(interlockingActivity.this, "Error in Result Handling", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(interlockingActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MinorActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}