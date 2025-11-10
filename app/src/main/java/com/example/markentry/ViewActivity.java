package com.example.markentry;

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

public class ViewActivity extends AppCompatActivity {
    EditText na;
    Button V;
    TextView sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.view_main);

        na = findViewById(R.id.editTextText4);
        V = findViewById(R.id.button4);
        sh = findViewById(R.id.textView6);

        SharedPreferences sp =getSharedPreferences("sp_a", MODE_PRIVATE);
        V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Total = sp.getInt("mark", -1);
                String nam = sp.getString("name", "NF");

                if (na.getText().toString().trim().equals(nam)){
                    sh.setText("Total marks of "+nam+" is : "+ Total);
                    return;
                }else {
                    Toast.makeText(ViewActivity.this, "No Data for the Student Found", Toast.LENGTH_SHORT).show();
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
