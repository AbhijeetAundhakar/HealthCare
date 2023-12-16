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

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText eDetails;
    Button btnBack, btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textviewBMDpackageName);
        eDetails = findViewById(R.id.multiLineBMDEditText);
        eDetails.setKeyListener(null);
        tvTotalCost = findViewById(R.id.textBMDCost);
        btnBack = findViewById(R.id.BackBMDBtn);
        btnAddToCart = findViewById(R.id.addToBMDCart);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        eDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText( "Total Cost : " + intent.getStringExtra("text3") + "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);

                if (db.cheeckCart(username, product) == 1){
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product already ahead", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username, product, price, "medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Record Inserted in cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BuyMedicineActivity.class));
                }
            }
        });
    }
}