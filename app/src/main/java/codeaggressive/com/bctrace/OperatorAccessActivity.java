package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OperatorAccessActivity extends AppCompatActivity {
    EditText pasText;
    Button submitPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_access);

        pasText = findViewById(R.id.OperatorPassText);
        submitPas = findViewById(R.id.OperatorpasButton);

        submitPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pasText.getText().toString().equals("SavOperator")){
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OperatorAccessActivity.this, SettingsActivity.class);
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