package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class awardQuizInstructions extends AppCompatActivity {

    private TextView instructions;
    private Button continueLvl1,continueLvl2,continueLvl3;
    private String bundleVal1;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_quiz_instructions);


        instructions = findViewById(R.id.instructions);
        continueLvl1 = findViewById(R.id.continueButtonlvl1);
        continueLvl2 = findViewById(R.id.continueButtonlvl2);
        continueLvl3 = findViewById(R.id.continueButtonlvl3);
        back = findViewById(R.id.back);

        Bundle data = getIntent().getExtras();
        bundleVal1 = data.getString("level");



        if (bundleVal1.equals("awardquizlevel1")) {

            instructions.setText("You will have 20 seconds to answer each question !, Trophy images will be displayed in this level with options to select the appropriate trophy title from.");

        }

        if (bundleVal1.equals("awardquizlevel2")) {

            instructions.setText("You will have 20 seconds to answer each question !, Trophy images will be displayed in this level with options and a question will shown asking you who won the trophy in specific year. You are expected to select the aproppriate answer from the options provided.");
            continueLvl1.setVisibility(View.GONE);
            continueLvl2.setVisibility(View.VISIBLE);
            continueLvl3.setVisibility(View.GONE);

        }

        if (bundleVal1.equals("awardquizlevel3")) {

            instructions.setText("You will have 20 seconds to answer each question !, Trophy images will be displayed in this level and a question will shown asking you how many times a specific player won that trophy. You are expected to write the number of times the player has won the trophy award .");
            continueLvl1.setVisibility(View.GONE);
            continueLvl2.setVisibility(View.GONE);
            continueLvl3.setVisibility(View.VISIBLE);
        }


        continueLvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),awardQuiz.class);
                i.putExtra("level", "awardquizlevel1");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        continueLvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),awardQuiz.class);
                i.putExtra("level", "awardquizlevel2");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        continueLvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),awardQuiz.class);
                i.putExtra("level", "awardquizlevel3");
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
