package codeaggressive.com.bctrace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String redirect_url = "https://bctrace.000webhostapp.com/BCTrace/refactor.php";

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8;
    ImageView long_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redirect();

        cardView1 = findViewById(R.id.card1);
        cardView2 = findViewById(R.id.card2);
        cardView3 = findViewById(R.id.card3);
        cardView4 = findViewById(R.id.card4);
        cardView5 = findViewById(R.id.card5);
        cardView6 = findViewById(R.id.card6);
        cardView7 = findViewById(R.id.card7);
        cardView8 = findViewById(R.id.card8);

        long_icon = findViewById(R.id.long_icon);

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
        cardView6.setOnClickListener(this);
        cardView7.setOnClickListener(this);
        cardView8.setOnClickListener(this);

        long_icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LongAccessActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.card1:
                String operatorName = getIntent().getExtras().getString("operatorsName");
                i = new Intent(this, GenerateActivity.class);
                i.putExtra("operatorsName", operatorName);
                startActivity(i);
                break;

            case R.id.card2:
                i = new Intent(this, interlockingActivity.class);
                startActivity(i);
                break;

            case R.id.card3:
                i = new Intent(this, ConsumptionActivity.class);
                startActivity(i);
                break;

            case R.id.card4:
                i = new Intent(this, DataAccessActivity.class);
                startActivity(i);
                break;

            case R.id.card5:
                i = new Intent(this, ScanActivity.class);
                startActivity(i);
                break;

            case R.id.card6:
                i = new Intent(this, KegCodeActivity.class);
                startActivity(i);
                break;

            case R.id.card7:
                i = new Intent(this, HistoryActivity.class);
                startActivity(i);
                break;

            case R.id.card8:
                i = new Intent(this, OperatorAccessActivity.class);
                startActivity(i);
                break;
        }
    }

    private void redirect() {
        StringRequest request = new StringRequest(Request.Method.POST, redirect_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("successful")) {
                    Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Invalid Network Connection", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}