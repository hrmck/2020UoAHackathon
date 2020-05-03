package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class StoreActivity extends AppCompatActivity {
    ListView store_list;
    String[] storeName;
    Integer[] imgID = {R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        storeName = getResources().getStringArray(R.array.stores);  //this has to be put inside onCreate or else .getResources() cannot work

        store_list = findViewById(R.id.store_list);
        storeListView storeList = new storeListView(this, storeName, imgID);
        store_list.setAdapter(storeList);
        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chosen_storeName = storeName[position];
                Intent intent = new Intent(StoreActivity.this, ProductActivity.class);
                intent.putExtra("chosenStoreName", chosen_storeName);
                startActivity(intent);
            }
        });
    }
}
