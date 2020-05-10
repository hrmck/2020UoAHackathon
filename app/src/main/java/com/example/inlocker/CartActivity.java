package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Button toPayment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toPayment = findViewById(R.id.toPaymentBtn);

        toPayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toPaymentBtn) {
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(intent);
        }
    }
}
