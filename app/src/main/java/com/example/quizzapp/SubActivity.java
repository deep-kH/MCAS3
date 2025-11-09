package com.example.quizzapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class questions {
    public  String txt;
    public  String[] opt;
    public int crct;
    public questions(String t, String[] o, int c){
        this.txt = t;
        this.opt = o;
        this.crct = c;
    }
}

public class SubActivity extends AppCompatActivity {
    int Score = 0;
    boolean answered = false;
    int counter = 0;
    questions[] qtlist = {
            new questions("What", new String[]{"hi", "bye", "shy"}, 1) ,
            new questions("Where", new String[]{"India", "Pak", "USA"}, 0),
            new questions("When", new String[]{"56", "78", "98"}, 0)
    };

    private void checkanswer(){
        answered = true;
        RadioButton rd = findViewById(opti.getCheckedRadioButtonId());
        int selected = -1;
        if (rd == b1) selected = 0;
        else if (rd == b2) selected = 1;
        else if(rd == b3) selected = 2;

        questions cntqn = qtlist[counter];

        if (selected == cntqn.crct){
            Score++;
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }
        counter++;
        if(counter < qtlist.length){
            sub.setText("Next Question");
            next();
        }else {
            sub.setText("See Final Score");
            qn.setVisibility(View.INVISIBLE);
            opti.setVisibility(View.INVISIBLE);
            score.setText("Your Final Score is : "+ Score + "/" + qtlist.length);
            score.setBackgroundColor(Color.parseColor("#673AB7FF"));
            score.setTextSize(24);
        }
    }

    private void next(){
        opti.clearCheck();
        opti.setVisibility(View.VISIBLE);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);

        if(counter < qtlist.length){
            questions ctqn = qtlist[counter];
            qn.setText(ctqn.txt);
            b1.setText(ctqn.opt[0]);
            b2.setText(ctqn.opt[1]);
            b3.setText(ctqn.opt[2]);

            sub.setText("Submit");
            score.setText("Score : " + Score + "/" + qtlist.length);
            answered = false;
        }
    }
    TextView score, qn, head;
    RadioGroup opti;
    RadioButton b1, b2, b3;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.quiz_main);
        qn = findViewById(R.id.textView7);
        score = findViewById(R.id.textView8);
        opti = findViewById(R.id.radiogp);
        b1 = findViewById(R.id.radioButton);
        b2 = findViewById(R.id.radioButton2);
        b3 = findViewById(R.id.radioButton3);
        sub = findViewById(R.id.button2);
        head = findViewById(R.id.textView6);

        Intent i = getIntent();
        String nam = i.getStringExtra("user");
        head.setText("Welcome " + nam);

        next();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (opti.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(SubActivity.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    } else {
                        checkanswer();
                    }
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
