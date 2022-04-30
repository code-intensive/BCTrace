package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ScanHistoryActivity extends AppCompatActivity {
    ListView hist_list;
    Button genHistory,conHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_history);

        hist_list = findViewById(R.id.scn_history_list);
        genHistory = findViewById(R.id.scn_gen_history_button);
        conHistory = findViewById(R.id.scn_con_history_button);
        Toast.makeText(getApplicationContext(), "Still worked on", Toast.LENGTH_LONG).show();

        genHistory.setOnClickListener(v -> {
            genIntent();
        });

        conHistory.setOnClickListener(v -> {
            conIntent();
        });
    }

    private void conIntent() {
        Intent i = new Intent(ScanHistoryActivity.this, ConsumptionHistoryActivity.class);
        startActivity(i);
        finish();
    }

    private void genIntent() {
        Intent i = new Intent(ScanHistoryActivity.this, HistoryActivity.class);
        startActivity(i);
        finish();
    }
}