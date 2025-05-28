package com.example.shoppingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextNewUsername;
    private EditText editTextNewPassword;
    private EditText editTextPhone;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("ShoppingApp", MODE_PRIVATE);

        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(v -> attemptRegister());
    }

    private void attemptRegister() {
        String username = editTextNewUsername.getText().toString();
        String password = editTextNewPassword.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (sharedPreferences.contains("password_" + username)) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password_" + username, password);
        editor.putString("phone_" + username, phone);
        editor.apply();

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
        finish();
    }
}