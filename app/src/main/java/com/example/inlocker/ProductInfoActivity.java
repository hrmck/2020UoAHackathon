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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.String;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener {
    Button addToCart, toCart, calculateTotal;
    TextView textViewName;
    TextView textViewPrice;
    TextView textViewRemainAmount;
    TextView textViewTotalPrice;
    EditText editTextInputAmount;
    int price;
    String remainAmount, currentDate;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    public static String cartProdName;
    public int cartProdAmount;
    public int cartProdPrice;
    public static String placeHolder1;
    public static String placeHolder2;

    private static String TAG;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    DocumentReference collectionProduct;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        TAG = getApplicationContext().toString();
        user = fAuth.getCurrentUser();
        uid = user.getUid();
        collectionProduct = fStore.collection("purchaseHistory").document(uid);

        String productItemID = getIntent().getStringExtra("productItemDocumentID");
        String storeID = getIntent().getStringExtra("storeDocumentID");
        documentReference = db.collection("storeList").document(storeID)
                .collection("Products").document(productItemID);


        textViewName = findViewById(R.id.name_pi_textView);
        textViewPrice = findViewById(R.id.price_pi_textView);
        textViewRemainAmount = findViewById(R.id.remainAmount_pi_textView);
        Calendar calender = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance().format(calender.getTime());

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
                /*toCart.putExtra(cartProdName, cartProdName);
                placeHolder2 = String.valueOf(cartProdAmount);
                toCart.putExtra(placeHolder2, String.valueOf(cartProdAmount));*/
                toCart.putExtra(placeHolder1, cartProdPrice);
                startActivity(toCart);
                break;
            case R.id.add_to_cartBtn:
                // String order = "Date:" +currentDate+ " Product Name: " +cartProdName + " Amount: " +cartProdAmount + " Total Price " +cartProdPrice + "Yen";
                String order = currentDate + " " + cartProdName + " * " + cartProdAmount + " Total Price " + cartProdPrice + "Yen";
                addToCartDatabase(order);
                //dont forget the loop fro create new doc vs update existing doc
                //add to cart
                Intent goToCart = new Intent(ProductInfoActivity.this, CartActivity.class);
                goToCart.putExtra(placeHolder1, cartProdPrice);
                startActivity(goToCart);
                break;
        }
    }

    private void calculateNewTotal() {
        int newAmount = Integer.parseInt(editTextInputAmount.getText().toString());
        cartProdAmount = newAmount;
        int newTotal = newAmount * price;
        cartProdPrice = newTotal;
        textViewTotalPrice.setText(String.valueOf(newTotal));


    }

    private void fetchProductInfo() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(getApplicationContext(),
                        "Product info loaded successfully", Toast.LENGTH_SHORT).show();
                textViewName.setText(documentSnapshot.getString("Name"));
                cartProdName = String.valueOf(documentSnapshot.get("Name"));



                Long priceGet = (Long) documentSnapshot.get("Price");
                price = priceGet.intValue();

                String price = String.valueOf(documentSnapshot.get("Price")) + " each";
                textViewPrice.setText(price);
                remainAmount = String.valueOf(documentSnapshot.get("Amount")) + " remaining";
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

    private void addToCartDatabase(String order) {
        Map<String, Object> item = new HashMap<>();
        item.put("Order", order);

        collectionProduct.set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Info saved", Toast.LENGTH_SHORT).show();


                //go back to seller view
               /* Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Some error occurred: " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
