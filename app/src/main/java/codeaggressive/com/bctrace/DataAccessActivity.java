package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataAccessActivity extends AppCompatActivity {
    EditText pasText;
    Button submitPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access);

        pasText = findViewById(R.id.PassText);
        submitPas = findViewById(R.id.pasButton);

        submitPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pasText.getText().toString().equals("Access")){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataAccessActivity.this, DatabaseActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}