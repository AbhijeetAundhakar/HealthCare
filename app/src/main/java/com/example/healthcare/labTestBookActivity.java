package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class labTestBookActivity extends AppCompatActivity {
    EditText edName, edAddress, edContct, edPinCode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edName = findViewById(R.id.editTextLTBFullName);
        edAddress = findViewById(R.id.editTextLTBAddress);
        edContct = findViewById(R.id.editTextLTBContact);
        edPinCode = findViewById(R.id.editTextLTBPinCode);
        btnBooking = findViewById(R.id.bookingLTB);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("Price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("Date");
        String time = intent.getStringExtra("Time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(username, edName.getText().toString(), edAddress.getText().toString(), edContct.getText().toString(), Integer.parseInt(edPinCode.getText().toString()), date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
                db.removeCart(username, "lab");

                Toast.makeText(labTestBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(labTestBookActivity.this, homeActivity.class));
            }
        });
    }
}