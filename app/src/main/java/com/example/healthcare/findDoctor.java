package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class findDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit = findViewById(R.id.cardFdbackButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), homeActivity.class));
            }
        });

        CardView familyPhysican = findViewById(R.id.cardFamilyPhysician);
        familyPhysican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), doctorDetailsActivity.class);
                itn.putExtra("title", "Family_Physican");
                startActivity(itn);
            }
        });

        CardView dietician = findViewById(R.id.cardDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), doctorDetailsActivity.class);
                itn.putExtra("title", "Dietician");
                startActivity(itn);
            }
        });

        CardView dentist = findViewById(R.id.cardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), doctorDetailsActivity.class);
                itn.putExtra("title", "Dentist");
                startActivity(itn);
            }
        });

        CardView surgeon = findViewById(R.id.cardSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), doctorDetailsActivity.class);
                itn.putExtra("title", "Surgeon");
                startActivity(itn);
            }
        });

        CardView cardiologists = findViewById(R.id.cardCardiologies);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), doctorDetailsActivity.class);
                itn.putExtra("title", "Cardiologists");
                startActivity(itn);
            }
        });
    }
}