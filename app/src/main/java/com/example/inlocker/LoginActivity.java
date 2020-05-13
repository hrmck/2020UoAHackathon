package com.example.inlocker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_btn;
    EditText editTextEmail, editTextPwd;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.login_progressBar);
        editTextEmail = findViewById(R.id.EmailEditText);
        editTextPwd = findViewById(R.id.PasswordEditText);

        login_btn = findViewById(R.id.LoginBtn_main);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.LoginBtn_main) {
            userLogin();
        }
        ;
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String pwd = editTextPwd.getText().toString().trim();

        if (isValidLoginCred(email, pwd)) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Intent toMenu = new Intent(LoginActivity.this, StoreActivity.class);
                                toMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(toMenu);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private Boolean isValidLoginCred(String email, String pwd) {
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }
        if (pwd.isEmpty()) {
            editTextPwd.setError("Password is required");
            editTextPwd.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }
        return true;
    }
}
