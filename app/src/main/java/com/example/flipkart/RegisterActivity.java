package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        Btn = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.already_account);
        // Set on Click Listener on Registration button
        Btn.setOnClickListener(v -> registerNewUser());
        ImageView cross = findViewById(R.id.cross2);
        cross.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, HomeFragment.class);
            startActivity(i);
            finish();
        });
        textView.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this,EmailActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void registerNewUser() {
        progressbar.setVisibility(View.VISIBLE);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email!!", Toast.LENGTH_SHORT).show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password!!", Toast.LENGTH_SHORT).show();
            progressbar.setVisibility(View.GONE);
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressbar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, EmailActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            Toast.makeText(RegisterActivity.this, "Weak password!", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            Toast.makeText(RegisterActivity.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthUserCollisionException e) {
                            Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(RegisterActivity.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("RegistrationActivity", e.getMessage());
                        }
                    }
                });

    }
}