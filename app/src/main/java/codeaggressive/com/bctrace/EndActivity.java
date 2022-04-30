package codeaggressive.com.bctrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EndActivity extends AppCompatActivity {
    ImageView passImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        passImage = findViewById(R.id.passImage);
        passImage.setOnLongClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LongAccessActivity.class);
            startActivity(intent);
            return true;
        });
    }
}