package com.example.inlocker;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class stores extends AppCompatActivity {
    ListView store_list;
    String[] storeName;
    Integer[] imgID = {R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stores);
        storeName = getResources().getStringArray(R.array.stores);

        store_list = findViewById(R.id.store_list);
        storeListView storeList = new storeListView(this, storeName, imgID);
        store_list.setAdapter(storeList);
    }
}
