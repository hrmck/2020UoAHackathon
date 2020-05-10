package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener {
    Button addToCart, toCart, calculateTotal;
    TextView textViewName, textViewPrice, textViewRemainAmount;
    TextView textViewTotalPrice;
    EditText editTextInputAmount;
    int price;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        String productItemID = getIntent().getStringExtra("productItemDocumentID");
        String storeID = getIntent().getStringExtra("storeDocumentID");
        documentReference = db.collection("storeList").document(storeID)
                .collection("Products").document(productItemID);


        textViewName = findViewById(R.id.name_pi_textView);
        textViewPrice = findViewById(R.id.price_pi_textView);
        textViewRemainAmount = findViewById(R.id.remainAmount_pi_textView);

        fetchProductInfo();

        textViewTotalPrice = findViewById(R.id.totalPrice_value_pi_textView);

        editTextInputAmount = findViewById(R.id.inputAmount_pi_editText);

        addToCart = findViewById(R.id.add_to_cartBtn);
        toCart = findViewById(R.id.check_cartBtn);
        calculateTotal = findViewById(R.id.calculateBtn);

        calculateTotal.setOnClickListener(this);
        toCart.setOnClickListener(this);
        addToCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculateBtn:
                calculateNewTotal();
                break;
            case R.id.check_cartBtn:
                Intent toCart = new Intent(ProductInfoActivity.this, CartActivity.class);
                startActivity(toCart);
                break;
            case R.id.add_to_cartBtn:
                //add to cart
                break;
        }
    }

    private void calculateNewTotal() {
        int newAmount = Integer.parseInt(editTextInputAmount.getText().toString());
        int newTotal = newAmount * price;
        textViewTotalPrice.setText(String.valueOf(newTotal));
    }

    private void fetchProductInfo() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(getApplicationContext(),
                        "Product info loaded successfully", Toast.LENGTH_SHORT).show();
                textViewName.setText(documentSnapshot.getString("Name"));

                Long priceGet = (Long) documentSnapshot.get("Price");
                price = priceGet.intValue();

                String price = String.valueOf(documentSnapshot.get("Price")) + " each";
                textViewPrice.setText(price);
                String remainAmount = String.valueOf(documentSnapshot.get("Amount")) + " remaining";
                textViewRemainAmount.setText(remainAmount);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
