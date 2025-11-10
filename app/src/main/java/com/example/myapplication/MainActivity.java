package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Spinner s;
    EditText t1;
    Button add;
    LinearLayout layoutop;
    Button sub;

    Map<String, Integer> Costmap = Map.of(
            "Select an Item",0,
            "Biscuit",89,
            "Tomato",15,
            "Toy Car",65,
            "Jam",78,
            "Bread",21
    );

    public void addItem(View view){
        String selected = s.getSelectedItem().toString();
        String quantS = t1.getText().toString().trim();
        Integer Cost = Costmap.get(selected);

        int quantity;
        try {
            quantity = Integer.parseInt(quantS);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for Quantity.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Cost == null) {
            Toast.makeText(this, "Data missing for " + selected, Toast.LENGTH_SHORT).show();
            return;
        }

        if (selected.equals("Select an Item")){
            Toast.makeText(this, "Please select an Item and Enter the Quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int quant = quantity*Cost;

        LinearLayout newRow = new LinearLayout(this);
        newRow.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams rowparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rowparams.setMargins(0,0,0,8);
        newRow.setLayoutParams(rowparams);
        LinearLayout.LayoutParams colparams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        colparams.setMargins(4,4,4,4);

        TextView col1 = new TextView(this);
        col1.setText(selected);
        col1.setTextSize(14);
        col1.setLayoutParams(colparams);
        col1.setPadding(4, 4, 4, 4);

        TextView col2 = new TextView(this);
        col2.setText(quantS);
        col2.setTextSize(14);
        col2.setLayoutParams(colparams);

        TextView col3 = new TextView(this);
        col3.setText(String.valueOf(quant));
        col3.setTextSize(14);
        col3.setLayoutParams(colparams);


        newRow.setBackgroundColor(getResources().getColor(android.R.color.white));
        newRow.setPadding(8, 8, 8, 8);
        newRow.addView(col1);
        newRow.addView(col2);
        newRow.addView(col3);

        layoutop.addView(newRow);
        t1.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        s = (Spinner) findViewById(R.id.spinner);
        t1 = (EditText)findViewById(R.id.Quantity);
        add = (Button)findViewById(R.id.button);
        layoutop = (LinearLayout)findViewById(R.id.oplayout);
        sub = (Button)findViewById(R.id.submit);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(
                this,
                R.array.Items,
                android.R.layout.simple_spinner_item
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(ad);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}