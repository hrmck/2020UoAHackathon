package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class generateQRCodeActivity extends AppCompatActivity implements View.OnClickListener {
    Button toMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_q_r_code);

        toMain = findViewById(R.id.toMain_qrBtn);
        toMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toMain_qrBtn) {
            Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
