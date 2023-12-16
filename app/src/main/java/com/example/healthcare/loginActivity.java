package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText username,password;
    Button loginButton;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerLink = findViewById(R.id.registerLink);
        loginButton = findViewById(R.id.loginButton);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                String pasCode = password.getText().toString();
                dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);     // SOMETHING DATABASE  NAME WILL MAY CREATE PROBLEM

                if (pasCode.length() == 0 | uName.length() == 0) {
                    Toast.makeText(loginActivity.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }else{
                    if (db.login(uName, pasCode) == 1) {
                        Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", uName);        // MAY BE USERNAME WILL OCCURE THE ERROR
                        // to save our data with key and value.
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), homeActivity.class));
                    }else{
                        Toast.makeText(loginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent goToRegistrationPage = new Intent(loginActivity.this, registration.class);
//                startActivity(goToRegistrationPage);

                // YOU CAN ALSO USE LIKE THIS TO GO FROM ONE ACTIVITY TO ANOTHER.
                startActivity(new Intent(loginActivity.this, registration.class));
            }
        });
        
    }
}