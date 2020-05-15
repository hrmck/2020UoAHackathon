package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class shopperinfo extends AppCompatActivity implements View.OnClickListener {


    private static String TAG;
    Button CreateProfile;
    EditText Name, Surname, PhoneNumber, Address;
    ProgressBar progressBar;


    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    FirebaseUser user;
    DocumentReference collectionProduct;
    String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppers_activity);

        TAG = getApplicationContext().toString();
        user = fAuth.getCurrentUser();
        uid = user.getUid();
        collectionProduct = fStore.collection("buyerList").document(uid);


        Name = findViewById(R.id.NameEditText);
        Surname = findViewById(R.id.SurnameEditText);
        PhoneNumber = findViewById(R.id.PhoneNumberEditText);
        Address = findViewById(R.id.AddressEditText);


        progressBar = findViewById(R.id.signUp_progressBar);

        CreateProfile = findViewById(R.id.CreateProfileBtn);
        CreateProfile.setOnClickListener((View.OnClickListener) this);
    }


    //@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CreateProfileBtn:
                saveItem();
                break;
            /*case R.id.itemClear_NewUploadItemBtn:
                clearContents();
                break;*/
        }
    }

    /*EditText related
    private void clearContents() {
        Name.getText().clear();
        Surname.getText().clear();
        Address.getText().clear();
        PhoneNumber.getText().clear();
    }*/

    // private void registerUser() {


    // if (isValidSignUpCred(Name, Surname, PhoneNumber)) {
    //Firebase related functions
    private void saveItem() {
        String Name = this.Name.getText().toString().trim(); //this.Name -> NameEditText if not working
        String Surname = this.Surname.getText().toString().trim();
        String PhoneNumber = this.PhoneNumber.getText().toString().trim();
        String Address = this.Address.getText().toString().trim();


        //validation
        if (Name.trim().isEmpty() || Surname.trim().isEmpty() || Address.trim().isEmpty() || PhoneNumber.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a valid name, price, amount and category", Toast.LENGTH_SHORT).show();
            return;
        }


        uploadToFirestore(Name, Surname, Address, PhoneNumber);
    }

    private void uploadToFirestore(String Name, String Surname, String Address, String PhoneNumber) {
        Map<String, Object> item = new HashMap<>();
        item.put("Name", Name);
        item.put("Surname", Surname);
        item.put("Address", Address);
        item.put("PhoneNumber", PhoneNumber);

        collectionProduct.set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Info saved", Toast.LENGTH_SHORT).show();


                //go back to seller view
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
//   }
//}

    /*private boolean isValidSignUpCred(String Name, String pwd, String pwdC) {
        if (Name.isEmpty()) {
            Name.setError("Name is required");
            Name.requestFocus();
            return false;
        }
        if (pwd.isEmpty()) {
            Surname.setError("Surname is required");
            Surname.requestFocus();
            return false;
        }
        if (pwdC.isEmpty()) {
            PhoneNumber.setError("Confirm phone number is required");
            PhoneNumber.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Name.setError("Please enter a valid email");
            Name.requestFocus();
            return false;
        }
        if (pwd.length() < 6) {
            Surname.setError("Minimum length of password should be 6");
            Surname.requestFocus();
            return false;
        }
        if (!pwd.equals(pwdC)) {
            //not same password, error
            PhoneNumber.setError("Both passwords are not the same");
            PhoneNumber.requestFocus();
            return false;
        }
        return true;*/




