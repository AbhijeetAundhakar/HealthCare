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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
        {"A-Methapred (Methylprednisolone Sodium Succinate)","","","","50"},
        {"Abacavir and Lamivudine Film-coated Tablets (Kivexa)","","","","305"},
        {"Abacavir Sulfate (Ziagen)","","","","540"},
        {"Abacavir Sulfate and Lamivudine Tablets (Epzicom)","","","","360"},
        {"Abacavir Sulfate, Lamivudine, and Zidovudine (Trizivir)","","","","60"},
        {"Abametapir Lotion (Xeglyze)","","","","120"},
        {"A-Methapred (Methylprednisolone Sodium Succinate)","","","","50"},
        {"Abacavir and Lamivudine Film-coated Tablets (Kivexa)","","","","305"},
    };

    private String[] packages_details = {
            "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n" +
                    "Dosage recommendations for A-Methapred vary for each individual. Talk to your doctor about your individual dosage recommendation.\n" +
                    "Cyclosporin can interact with A-Methapred. Tell your doctor all medications you are taking. Do not take A-Methapred while getting vaccinated for smallpox.",
                    "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n" +
                    "Our A-Methapred (methylprednisolone sodium succinate) Side Effects Drug Center provides a comprehensive view",
                    "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n" +
                    "Our A-Methapred (methylprednisolone sodium succinate) Side Effects Drug Center provides a comprehensive view \n" +
                    "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n" +
                            "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n" +
                            "Dosage recommendations for A-Methapred vary for each individual. Talk to your doctor about your individual dosage recommendation.\n"+
                            "A-Methapred (methylprednisolone sodium succinate) is an anti-inflammatory steroid used to treat conditions\n"
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        lst = findViewById(R.id.listViewBMLabTest);
        btnBack = findViewById(R.id.backBMBtn);
        btnGoToCart = findViewById(R.id.goToBMCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CardBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), homeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lineas,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", packages_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
    }
}