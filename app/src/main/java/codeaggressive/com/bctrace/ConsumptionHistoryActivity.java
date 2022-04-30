package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ConsumptionHistoryActivity extends AppCompatActivity {
    ListView hist_list;
    Button genHistory, scanHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_history);

        hist_list = findViewById(R.id.con_history_list);
        genHistory = findViewById(R.id.con_gen_history_button);
        scanHistory = findViewById(R.id.con_scn_history_button);
        Toast.makeText(getApplicationContext(), "Still worked on", Toast.LENGTH_LONG).show();

        genHistory.setOnClickListener(v -> {
            genIntent();
        });

        scanHistory.setOnClickListener(v -> {
            scanIntent();
        });

    }

    private void scanIntent() {
        Intent i = new Intent(ConsumptionHistoryActivity.this, ScanHistoryActivity.class);
        startActivity(i);
        finish();
    }

    private void genIntent() {
        Intent i = new Intent(ConsumptionHistoryActivity.this, HistoryActivity.class);
        startActivity(i);
        finish();
    }
}