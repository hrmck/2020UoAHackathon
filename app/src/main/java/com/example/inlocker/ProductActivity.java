package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        String received_storeName = getIntent().getStringExtra("chosenStoreName");
        storeName.setText(received_storeName);

        //categories = getResources().getStringArray(R.array.categories);

        //I have started attempting to retrieve the data from the firebase
        goods_list = (RecyclerView) findViewById(R.id.productListView);
        DrinksBtn = (ToggleButton) findViewById(R.id.DrinksBtn);
        a = (TextView) findViewById(R.id.a);
        b = (TextView) findViewById(R.id.b);
        c = (TextView) findViewById(R.id.c);

        DrinksBtn.setOnClickListener(new View.OnClickListener() {
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

//my editing ends here
        cart = (ImageButton) findViewById(R.id.cartBtn);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
