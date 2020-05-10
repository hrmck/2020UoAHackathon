package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class StoreActivity extends AppCompatActivity {
    //ListView store_list;
    //String[] storeName;
    //Integer[] imgID = {R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo, R.drawable.store_demo};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference storeListRef = db.collection("storeList");

    private StoreListItemAdapter adapter;

    SearchView search_bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        /*
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

         */

        setUpRecyclerView();

        search_bar = findViewById(R.id.store_searchView);
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_bar.onActionViewExpanded();
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = storeListRef;

        FirestoreRecyclerOptions<StoreListItem> options = new FirestoreRecyclerOptions.Builder<StoreListItem>()
                .setQuery(query, StoreListItem.class)
                .build();
        adapter = new StoreListItemAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.storeList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new StoreListItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                StoreListItem itemClick = documentSnapshot.toObject(StoreListItem.class);
                String chosenStoreName = itemClick.getName();
                Intent intent = new Intent(StoreActivity.this, ProductActivity.class);
                intent.putExtra("chosenStoreName", chosenStoreName);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
