package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ProductActivity extends AppCompatActivity {

    TextView storeName;
    String[] categories;
    ImageButton cart;
    //My editing begins here.
    RecyclerView goods_list;
    //DatabaseReference reff;
    ToggleButton DrinksBtn;
    TextView a;
    private static final String TAG = "ProductActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        storeName = findViewById(R.id.storeName);
        cart = findViewById(R.id.cartBtn);
        categories = getResources().getStringArray(R.array.categories);

        String received_storeName = getIntent().getStringExtra("chosenStoreName");
        storeName.setText(received_storeName);

        //I have started attempting to retrieve the data from the firebase
        //goods_list = (RecyclerView) findViewById(R.id.productListView);
        DrinksBtn = (ToggleButton) findViewById(R.id.DrinksBtn);
        a = (TextView) findViewById(R.id.a);

        DrinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionReference docref = FirebaseFirestore.getInstance().collection("storeList/5bineW8oGWA3vRRj1XOY/Products");
                docref.whereEqualTo("Category", "Drinks").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        Log.d("Document", doc.getId() + "=>" + doc.getData());
                                        String info = doc.getData().toString();
                                        a.setText(info);
                                    }
                                } else {
                                    Log.d("Document", "No data");
                                    a.setText("No data");
                                }
                            }
                        });
            }
        });


//my editing ends here
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}


//This chunck works
/*FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docref = FirebaseFirestore.getInstance().document("storeList/5bineW8oGWA3vRRj1XOY/Products/7xx9ke9TACNyRIJRdQEW");
                docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()) {
                                Log.d("Document", doc.getData().toString());
                                String info = doc.getData().toString();
                                a.setText(info);

                            }
                                else{
                                    Log.d("Document", "No data");
                                a.setText("No data");
                                }
                            }
                        }
                    });
                }*/


        /*DrinksBtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        //getting the end branch we want to search
        reff = FirebaseDatabase.getInstance().getReference("Testing").child("FamilyMart");
        reff.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //taking the values we are interested in
        String name = dataSnapshot.child("name").getValue().toString();
        String category = dataSnapshot.child("category").getValue().toString();
        String price = dataSnapshot.child("price").getValue().toString();

        //placing these values into the display positions. Here they are displayed on the textviewer on top of the recycleview
        a.setText(name);
        b.setText(category);
        c.setText(price);
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });
        }
        });
        */


        /*for using firestore
        DrinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference Storeref = db.collection("storeList");
                //db.collection("storeList");
                Storeref.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    String info = task.getResult().toString();
                                    a.setText(info);
                                    System.out.println(info);
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }

                               /* if (!task.isSuccessful()) {
                                a.setText("Failed to retrieve data");
                                //Log.w(TAG, "Error getting documents.", task.getException());
                                }*/