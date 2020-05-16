package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    Button toPayment;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    DocumentReference collectionProduct;
    FirebaseUser user;
    String uid;
    private static String TAG;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    TextView cartProductInfo;
    TextView cartProductTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        TAG = getApplicationContext().toString();
        user = fAuth.getCurrentUser();
        uid = user.getUid();
        //collectionProduct = fStore.collection("purchaseHistory").document(uid);
        documentReference = db.collection("buyerList").document(uid);

        cartProductInfo = (TextView) findViewById(R.id.cartProductName);
        cartProductTotal = (TextView) findViewById(R.id.cartProductTotal);
        Intent intent = getIntent();
        int price = intent.getIntExtra(ProductInfoActivity.placeHolder1, 0);
        /*String name = intent.getStringExtra(ProductInfoActivity.cartProdName);
        String amount = intent.getStringExtra(ProductInfoActivity.placeHolder2);
        */


        /*TextView cartProductAmount = (TextView) findViewById(R.id.cartProductAmount);
        TextView cartProductTotal = (TextView) findViewById(R.id.cartProductTotal);

        cartProductName.setText("Product name: " + name);
        cartProductAmount.setText("Amount: " + amount);*/
        cartProductTotal.setText("Total " + price + "Yen");

        fetchProductInfo();

        toPayment = findViewById(R.id.toPaymentBtn);

        toPayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toPaymentBtn) {
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            startActivity(intent);
        }
    }

    private void fetchProductInfo() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(getApplicationContext(),
                        "Product info loaded successfully", Toast.LENGTH_SHORT).show();
                cartProductInfo.setText(documentSnapshot.getString("Order"));

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
