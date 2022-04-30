package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ListView hist_list;
    Button scanHistory, conHistory;
    ArrayList<String> data_list;
    ArrayAdapter dataAdpater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        hist_list = findViewById(R.id.history_list);
        scanHistory = findViewById(R.id.hist_scn_history_button);
        conHistory = findViewById(R.id.hist_con_history_button);
        myDB = new DatabaseHelper(this);
        data_list = new ArrayList<>();

        scanHistory.setOnClickListener(v -> {
            scanIntent();
        });

        conHistory.setOnClickListener(v -> {
            conIntent();
        });
        viewGeneratedImage();
    }

    private void viewGeneratedImage() {
        Cursor cursor = myDB.retrieveGenerateImage();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                data_list.add(cursor.getString(0) + " - " + cursor.getBlob(1));
            }
            dataAdpater = new ArrayAdapter<>(this, R.layout.history_selected_item, data_list);
            hist_list.setAdapter(dataAdpater);
        }

    }

    private void conIntent() {
        Intent i = new Intent(HistoryActivity.this, ConsumptionHistoryActivity.class);
        startActivity(i);
        finish();
    }

    private void scanIntent() {
        Intent i = new Intent(HistoryActivity.this, ScanHistoryActivity.class);
        startActivity(i);
        finish();
    }
}