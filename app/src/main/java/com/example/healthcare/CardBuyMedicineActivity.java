package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CardBuyMedicineActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    TextView tvTotal;
    private DatePickerDialog datePickerDialog;
    private Button dateButton, btnCheckOut, buttonBack;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_buy_medicine);

        dateButton = findViewById(R.id.buttonCartBMDatePicker);
        btnCheckOut = findViewById(R.id.btnBMCheckOut);
        buttonBack = findViewById(R.id.buttonBMCartBack);
        tvTotal = findViewById(R.id.textViewBMCartTotalPrice);
        lst = findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        dataBase db = new dataBase(getApplicationContext(), "healthcare", null, 1);

        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username, "medicine");
//        Toast.makeText(this, ""+dbData, Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost : "+totalAmount);

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add( item );
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lineas,
                new String[] {"line1","line2","line1","line4","line5"},
                new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        lst.setAdapter(sa);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BuyMedicineActivity.class));
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent itn = new Intent(getApplicationContext(), BuyMedicineActivity.class);
//                itn.putExtra("Price", tvTotal.getText());
//                itn.putExtra("Date", dateButton.getText());
//                startActivity(itn);
            }
        });

        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2+ "/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86500000);
    }
}

