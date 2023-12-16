package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doctorDetailsActivity extends AppCompatActivity {

    public String [][] doctorDetails = {
            {"Doctor Name : Ajit Singh", "Hospital Address : Belgaum", "Exp : 5 years", "Mobile No : 4594578", "600"},
            {"Doctor Name : Swapnil Patil", "Hospital Address : Dharwad", "Exp : 4 years", "Mobile No : 459457896", "700"},
            {"Doctor Name : Vikram Rathod", "Hospital Address : Hubli", "Exp : 3 years", "Mobile No : 674578", "800"},
            {"Doctor Name : Rahul Joshi", "Hospital Address : Mysore", "Exp : 8 years", "Mobile No : 4594566", "900"},
            {"Doctor Name : Sunil Kambale", "Hospital Address : Bengalore", "Exp : 1 years", "Mobile No : 45673458", "200"}
    };

    public String [][] doctorDetails2 = {
            {"Doctor Name : Ajit Singh", "Hospital Address : Belgaum", "Exp : 5 years", "Mobile No : 4594578", "600"},
            {"Doctor Name : Swapnil Patil", "Hospital Address : Dharwad", "Exp : 4 years", "Mobile No : 459457896", "700"},
            {"Doctor Name : Vikram Rathod", "Hospital Address : Hubli", "Exp : 3 years", "Mobile No : 674578", "800"},
            {"Doctor Name : Rahul Joshi", "Hospital Address : Mysore", "Exp : 8 years", "Mobile No : 4594566", "900"},
            {"Doctor Name : Sunil Kambale", "Hospital Address : Bengalore", "Exp : 1 years", "Mobile No : 45673458", "200"}
    };

    public String [][] doctorDetails3 = {
            {"Doctor Name : Ajit Singh", "Hospital Address : Belgaum", "Exp : 5 years", "Mobile No : 4594578", "600"},
            {"Doctor Name : Swapnil Patil", "Hospital Address : Dharwad", "Exp : 4 years", "Mobile No : 459457896", "700"},
            {"Doctor Name : Vikram Rathod", "Hospital Address : Hubli", "Exp : 3 years", "Mobile No : 674578", "800"},
            {"Doctor Name : Rahul Joshi", "Hospital Address : Mysore", "Exp : 8 years", "Mobile No : 4594566", "900"},
            {"Doctor Name : Sunil Kambale", "Hospital Address : Bengalore", "Exp : 1 years", "Mobile No : 45673458", "200"}
    };

    public String [][] doctorDetails4 = {
            {"Doctor Name : Ajit Singh", "Hospital Address : Belgaum", "Exp : 5 years", "Mobile No : 4594578", "600"},
            {"Doctor Name : Swapnil Patil", "Hospital Address : Dharwad", "Exp : 4 years", "Mobile No : 459457896", "700"},
            {"Doctor Name : Vikram Rathod", "Hospital Address : Hubli", "Exp : 3 years", "Mobile No : 674578", "800"},
            {"Doctor Name : Rahul Joshi", "Hospital Address : Mysore", "Exp : 8 years", "Mobile No : 4594566", "900"},
            {"Doctor Name : Sunil Kambale", "Hospital Address : Bengalore", "Exp : 1 years", "Mobile No : 45673458", "200"}
    };

    public String [][] doctorDetails5 = {
            {"Doctor Name : Ajit Singh", "Hospital Address : Belgaum", "Exp : 5 years", "Mobile No : 4594578", "600"},
            {"Doctor Name : Swapnil Patil", "Hospital Address : Dharwad", "Exp : 4 years", "Mobile No : 459457896", "700"},
            {"Doctor Name : Vikram Rathod", "Hospital Address : Hubli", "Exp : 3 years", "Mobile No : 674578", "800"},
            {"Doctor Name : Rahul Joshi", "Hospital Address : Mysore", "Exp : 8 years", "Mobile No : 4594566", "900"},
            {"Doctor Name : Sunil Kambale", "Hospital Address : Bengalore", "Exp : 1 years", "Mobile No : 45673458", "200"}
    };

    TextView tv;
    Button btn;

    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.defaultTitle);
        btn = findViewById(R.id.backBtn);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), findDoctor.class));
            }
        });

        if (title.compareTo("family physican") == 0) {
            doctor_details = doctorDetails;
        } else if (title.compareTo("Dietician") == 0) {
            doctor_details = doctorDetails2;
        } else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctorDetails3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctorDetails4;
        } else {
            doctor_details = doctorDetails5;
        }

        list = new ArrayList();
        for (int i = 0; i < doctorDetails.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctorDetails[i][0]);
            item.put("line2", doctorDetails[i][1]);
            item.put("line3", doctorDetails[i][2]);
            item.put("line4", doctorDetails[i][3]);
            item.put("line5", "Cons Fees : " + doctorDetails[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lineas,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});

        ListView lst = findViewById(R.id.listViewDoctorDetails);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent itn = new Intent(doctorDetailsActivity.this, bookApoinment.class);
                itn.putExtra("text1", title);
                itn.putExtra("text2", doctorDetails[i][0]);
                itn.putExtra("text3", doctorDetails[i][1]);
                itn.putExtra("text4", doctorDetails[i][3]);
                itn.putExtra("text5", doctorDetails[i][4]);
                startActivity(itn);
            }
        });
    }
}