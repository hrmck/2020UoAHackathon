package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    TextView storeName;
    String[] categories;
    ImageButton cart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        storeName = findViewById(R.id.storeName);
        String received_storeName = getIntent().getStringExtra("chosenStoreName");
        storeName.setText(received_storeName);

        //categories = getResources().getStringArray(R.array.categories);

        cart = findViewById(R.id.cartBtn);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
