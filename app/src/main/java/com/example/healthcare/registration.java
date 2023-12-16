package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    EditText regUsername;
    EditText regPassword;
    EditText regConfirmPassword;
    EditText regEmail;
    Button registerButton;
    TextView signInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        registerButton = findViewById(R.id.registerButton);
        signInLink = findViewById(R.id.signInLink);

        // EVENT FOR
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registration.this, loginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rUsrName = regUsername.getText().toString();
                String mail = regEmail.getText().toString();
                String rPswd = regPassword.getText().toString();
                String cnfPasswd = regConfirmPassword.getText().toString();
                dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);               // SOMETHIING PROBLE WILL OCCURE

                if (rUsrName.length() == 0 | mail.length() == 0 || rPswd.length()  == 0 || cnfPasswd.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill the details", Toast.LENGTH_SHORT).show();
                }else{
                    if (rPswd.compareTo(cnfPasswd) == 0) {
                        if (isValid(rPswd)) {
                            db.register(rUsrName,mail,rPswd);
                            Toast.makeText(registration.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), loginActivity.class));
                        }else{
                            Toast.makeText(registration.this, "Password must contain atleast 8 character, having letter, digti and special symbol", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // IN THIS WE ARE USING BUILT IN FUNCTION FOR THE PASSWORD FIELD, MIN 1 CAPITAL LETTER, 1 SPECIAL SYMBOL ETC
    public static boolean isValid(String passwordHere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordHere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordHere.length(); p++) {
                if (Character.isLetter(passwordHere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordHere.length(); r++) {
                if (Character.isDigit(passwordHere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordHere.length(); s++) {
                char c = passwordHere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }

}