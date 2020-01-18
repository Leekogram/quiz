package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class countryQuizInstructions extends AppCompatActivity {

    private TextView instructions;
    private Button continueLvl1,continueLvl2,continueLvl3;
    private String bundleVal1;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz_instructions);

        instructions = findViewById(R.id.instructions);
        continueLvl1 = findViewById(R.id.continueButtonlvl1);
        continueLvl2 = findViewById(R.id.continueButtonlvl2);
        continueLvl3 = findViewById(R.id.continueButtonlvl3);
        back = findViewById(R.id.back);

        Bundle data = getIntent().getExtras();
        bundleVal1 = data.getString("level");



        if (bundleVal1.equals("countryquizlevel1")) {

            instructions.setText("You will have 20 seconds to answer each question !, A country flag will be shown in this level with options of country names to select from.");

        }

        if (bundleVal1.equals("countryquizlevel2")) {

            instructions.setText("You will have 20 seconds to answer each question !, A country flag will be displayed in this level with options, you are expected to select the nmber of times the country have won the african cup of nation.");
            continueLvl1.setVisibility(View.GONE);
            continueLvl2.setVisibility(View.VISIBLE);
            continueLvl3.setVisibility(View.GONE);

        }

        if (bundleVal1.equals("countryquizlevel3")) {

            instructions.setText("You will have 20 seconds to answer each question !, An Image of players will be displayed in this level and you are to write the complete name of the player.");
            continueLvl1.setVisibility(View.GONE);
            continueLvl2.setVisibility(View.GONE);
            continueLvl3.setVisibility(View.VISIBLE);
        }


        continueLvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),countryQuiz.class);
                i.putExtra("level", "countryquizlevel1");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        continueLvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),countryQuiz.class);
                i.putExtra("level", "countryquizlevel2");
                i.putExtra("question","How many times have this country won the African cup of nation(AFCON).");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        continueLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),countryQuizLevel3.class);
                i.putExtra("level", "countryquizlevel3");
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
