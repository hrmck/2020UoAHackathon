package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductInfoActivity extends AppCompatActivity {
    Button addToCart;
    Button toCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        addToCart = findViewById(R.id.add_to_cartBtn);
        toCart = findViewById(R.id.check_cartBtn);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add stuff to cart
            }
        });

        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCart = new Intent(ProductInfoActivity.this, CartActivity.class);
                startActivity(toCart);
            }
        });
    }
}
