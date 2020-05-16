package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference storeDocumentRef;
    private CollectionReference productListRef;
    private ProductListItemAdapter adapter;

    TextView storeName;
    String[] categories;
    ImageButton cart;
    String storeDocumentID;
    ImageView storeImageView;
    Button[] categoryBtns;

    private static final String TAG = "ProductActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        storeName = findViewById(R.id.storeName);
        cart = findViewById(R.id.cartBtn);
        //categories = getResources().getStringArray(R.array.categories);
        storeImageView = findViewById(R.id.storeImage);

        categoryBtns = new Button[5];
        categoryBtns[0] = findViewById(R.id.category1_productBtn);
        categoryBtns[1] = findViewById(R.id.category2_productBtn);
        categoryBtns[2] = findViewById(R.id.category3_productBtn);
        categoryBtns[3] = findViewById(R.id.category4_productBtn);
        categoryBtns[4] = findViewById(R.id.category5_productBtn);

        storeDocumentID = getIntent().getStringExtra("documentID");
        storeDocumentRef = db.collection("storeList").document(storeDocumentID);
        productListRef = storeDocumentRef.collection("Products");

        setStoreInfo();
        setUpRecyclerView();

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setStoreInfo() {
        storeDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                storeName.setText(documentSnapshot.getString("Name"));
                if (documentSnapshot.getString("storeImageLink") != null) {
                    Picasso.get().load(documentSnapshot.getString("storeImageLink")).into(storeImageView);
                } else {
                    storeImageView.setImageResource(R.drawable.store_demo);
                }

                if (documentSnapshot.get("categories") != null) {
                    ArrayList results = (ArrayList) documentSnapshot.get("categories");
                    for (int ix = 0; ix < categoryBtns.length; ix++) {
                        categoryBtns[ix].setText(results.get(ix).toString());
                    }
                } else {
                    for (Button categoryBtn : categoryBtns) {
                        categoryBtn.setText("null");
                    }
                }
            }
        });

    }


    private void setUpRecyclerView() {
        FirestoreRecyclerOptions<ProductListItem> options = new FirestoreRecyclerOptions.Builder<ProductListItem>()
                .setQuery(productListRef, ProductListItem.class)
                .build();

        adapter = new ProductListItemAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.productList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductListItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);
                intent.putExtra("productItemDocumentID", documentSnapshot.getId());
                intent.putExtra("storeDocumentID", storeDocumentID);
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

//I have started attempting to retrieve the data from the firebase
//My editing begins here.
//RecyclerView goods_list;
// DatabaseReference reff;
//    ToggleButton DrinksBtn;
//    TextView a;
//goods_list = (RecyclerView) findViewById(R.id.productListView);
        /*
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
         */
//my editing ends here

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