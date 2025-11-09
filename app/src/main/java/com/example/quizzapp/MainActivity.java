package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    Spinner s;
    Button b;
    String dep, Us, pa;

    ArrayList<String> dpt = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        s= findViewById(R.id.spinner);
        user = findViewById(R.id.editTextText);
        pass = findViewById(R.id.editTextTextPassword);
        b = findViewById(R.id.button);

        dpt.add("Select");
        dpt.add("MCA");
        dpt.add("CSE");
        dpt.add("EEE");
        dpt.add("ME");

        ArrayAdapter ad = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dpt);
        s.setAdapter(ad);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dep = dpt.get(i);
                if(dep.equals("Select")){
                    Toast.makeText(MainActivity.this, "please select a department", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "please select a department", Toast.LENGTH_SHORT).show();
            }
        });
        
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Us = user.getText().toString().trim();
                pa = pass.getText().toString().trim();
                
                if (Us.isEmpty() || pa.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter the Credentials", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Us.length() < 5 || pa.length() < 7) {
                    Toast.makeText(MainActivity.this, "Enter Correct Credentials", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent i = new Intent(MainActivity.this, SubActivity.class);
                    i.putExtra("user", Us);
                    startActivity(i);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}