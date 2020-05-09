package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class ProductActivity extends AppCompatActivity {

    TextView storeName;
    String[] categories;
    ImageButton cart;
    //My editing begins here.
    RecyclerView goods_list;
    DatabaseReference reff;
    ToggleButton DrinksBtn;
    TextView a;
    TextView b;
    TextView c;
    TextView d;


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
        goods_list = (RecyclerView) findViewById(R.id.productListView);
        DrinksBtn = (ToggleButton) findViewById(R.id.DrinksBtn);
        a = (TextView) findViewById(R.id.a);
        b = (TextView) findViewById(R.id.b);
        c = (TextView) findViewById(R.id.c);

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

        //for using firestore
        DrinksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference Storeref = db.document("storeList/5bineW8oGWA3vRRj1XOY/Products");
                //db.collection("storeList");
                Storeref.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    //String info = documentSnapshot.getCollection
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
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
