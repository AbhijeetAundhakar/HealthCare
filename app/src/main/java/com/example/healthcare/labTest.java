package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class labTest extends AppCompatActivity {

    private String[][] packages = {
        {"Package 1: Full Body Ckaekup", "", "", "", "999"},
        {"Package 2: Blood Glucose Fasting", "", "", "", "299"},
        {"Package 3: COVID-19 Antibody", "", "", "", "899"},
        {"Package 4: Thyroid Check", "", "", "", "499"},
        {"Package 5: Immunity Check", "", "", "", "699"}
    };

    private String[] packageDetails = {
            "Blood Glucose Fasting\n" +
                    " Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Locate\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile\n",
            "Blood Glucose Fasting",
            "COVID-19 Antibody",
            "Thyroid Profile",
            "Complete Hemogram\n" +
                    "CRP Quantitative\n" +
                    "Iron Studies\n" +
                    "Kidnet Function test\n" +
                    "Vitamin D\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile\n"
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart = findViewById(R.id.goToCart);
        btnBack = findViewById(R.id.backBtn);
        listView = findViewById(R.id.listViewLabTest);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), homeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lineas,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent itn = new Intent(getApplicationContext(), labTestDetails.class);
                itn.putExtra("text1", packages[i][0]);
                itn.putExtra("text2", packageDetails[i]);
                itn.putExtra("text3", packages[i][4]);
                startActivity(itn);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), cartLabActivity.class));
            }
        });
    }
}