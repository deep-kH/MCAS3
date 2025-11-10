package com.example.markentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button E, V, Ent;
    EditText in, ex, name;
    TextView inte, exte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        E = findViewById(R.id.button);
        V = findViewById(R.id.button2);
        Ent = findViewById(R.id.button3);
        in = findViewById(R.id.editTextText2);
        ex = findViewById(R.id.editTextText3);
        name = findViewById(R.id.editTextText);
        inte = findViewById(R.id.textView2);
        exte = findViewById(R.id.textView3);

        inte.setVisibility(View.INVISIBLE);
        exte.setVisibility(View.INVISIBLE);
        in.setVisibility(View.INVISIBLE);
        ex.setVisibility(View.INVISIBLE);
        Ent.setVisibility(View.INVISIBLE);

        SharedPreferences sp = getSharedPreferences("sp_a", MODE_PRIVATE);

        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam = name.getText().toString().trim();
                if(nam.isEmpty()){
                    Toast.makeText(MainActivity.this, "Entter a Name", Toast.LENGTH_SHORT).show();
                }else {
                    inte.setVisibility(View.VISIBLE);
                    exte.setVisibility(View.VISIBLE);
                    in.setVisibility(View.VISIBLE);
                    ex.setVisibility(View.VISIBLE);
                    Ent.setVisibility(View.VISIBLE);

                    Ent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int internal = Integer.parseInt(in.getText().toString().trim());
                            int external = Integer.parseInt(ex.getText().toString().trim());
                            if (in.getText().toString().isEmpty() || ex.getText().toString().isEmpty()){
                                Toast.makeText(MainActivity.this, "Enter the Correct Marks", Toast.LENGTH_SHORT).show();
                            }else {
                                int Total = internal + external;
                                SharedPreferences.Editor se = sp.edit();
                                se.putInt("mark", Total);
                                se.putString("name", nam);
                                se.apply();
                                inte.setVisibility(View.INVISIBLE);
                                exte.setVisibility(View.INVISIBLE);
                                in.setVisibility(View.INVISIBLE);
                                ex.setVisibility(View.INVISIBLE);
                                Ent.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(i);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}