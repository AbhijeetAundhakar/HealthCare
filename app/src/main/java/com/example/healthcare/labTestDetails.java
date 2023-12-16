package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class labTestDetails extends AppCompatActivity {

    TextView tvPackageName, tvTotalcost;
    EditText edDetails;
    Button addToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewDDDetailsName);
        tvTotalcost = findViewById(R.id.textRes);
        edDetails = findViewById(R.id.multiLineEditText);
        addToCart = findViewById(R.id.addToCart);
        btnBack = findViewById(R.id.LTDBackBtn);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Total Cost :"+intent.getStringExtra("text3") + "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), labTest.class));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);
                if (db.cheeckCart(username, product) == 1) {
                    Toast.makeText(labTestDetails.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username, product, price, "lab");
                    Toast.makeText(labTestDetails.this, "Record inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), labTest.class));
                }
            }
        });
    }
}