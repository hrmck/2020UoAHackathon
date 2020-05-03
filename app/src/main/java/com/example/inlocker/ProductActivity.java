package com.example.inlocker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    TextView storeName;
    String[] categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        storeName = findViewById(R.id.store_name);
        String received_storeName = getIntent().getStringExtra("chosenStoreName");
        storeName.setText(received_storeName);

        categories = getResources().getStringArray(R.array.categories);

    }
}
