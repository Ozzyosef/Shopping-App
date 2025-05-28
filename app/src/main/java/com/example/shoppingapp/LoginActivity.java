package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("ShoppingApp", MODE_PRIVATE);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonNewUser = findViewById(R.id.buttonNewUser);

        buttonLogin.setOnClickListener(v -> attemptLogin());
        buttonNewUser.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void attemptLogin() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        String storedPassword = sharedPreferences.getString("password_" + username, "");

        if (storedPassword.equals(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}