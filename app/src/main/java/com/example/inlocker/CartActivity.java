package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.lang.String;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Button toPayment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ProductInfoActivity.cartProdName);
        String amount = intent.getStringExtra(ProductInfoActivity.placeHolder2);
        int price = intent.getIntExtra(ProductInfoActivity.placeHolder1, 0);

        TextView cartProductName = (TextView) findViewById(R.id.cartProductName);
        TextView cartProductAmount = (TextView) findViewById(R.id.cartProductAmount);
        TextView cartProductTotal = (TextView) findViewById(R.id.cartProductTotal);

        cartProductName.setText("Product name: " + name);
        cartProductAmount.setText("Amount: " + amount);
        cartProductTotal.setText("Total " + price + "Yen");


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
