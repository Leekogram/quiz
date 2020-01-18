package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class awardQuizLevels extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private Button awardQuizLevel1;
    private Button awardQuizLevel2;
    private Button awardQuizLevel3;
    private ImageView back;

    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_quiz_levels);

        try{

            mPrefs = getSharedPreferences("Quiz App",MODE_PRIVATE);


            val = mPrefs.getString("awardquizlevel", "n");

            awardQuizLevel1 = findViewById(R.id.awardQuizlevel1Button);
            awardQuizLevel2 = findViewById(R.id.awardQuizlevel2Button);
            awardQuizLevel3 = findViewById(R.id.awardQuizlevel3Button);
            back = findViewById(R.id.back);







            switch (val){
                case "awardquizlevel1":
                    awardQuizLevel2.setVisibility(View.VISIBLE);
                    awardQuizLevel3.setVisibility(View.INVISIBLE);

                    break;
                case  "awardquizlevel2":
                   awardQuizLevel2.setVisibility(View.VISIBLE);
                    awardQuizLevel3.setVisibility(View.VISIBLE);

                    break;
                case "awardquizlevel3":
                   awardQuizLevel2.setVisibility(View.VISIBLE);
                    awardQuizLevel3.setVisibility(View.VISIBLE);

                    break;

                default:
                    awardQuizLevel2.setVisibility(View.INVISIBLE);
                    awardQuizLevel3.setVisibility(View.INVISIBLE);

                    break;
            }

            awardQuizLevel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getApplicationContext(),awardQuizInstructions.class);
                    i.putExtra("level", "awardquizlevel1");
                    startActivity(i);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                }
            });

            awardQuizLevel2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),awardQuizInstructions.class);
                    i.putExtra("level", "awardquizlevel2");
                    i.putExtra("question","How many times have this country won the African cup of nation(AFCON).");
                    startActivity(i);
                    overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                }
            });

            awardQuizLevel3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),awardQuizInstructions.class);
                    i.putExtra("level", "awardquizlevel3");
                    startActivity(i);
                }
            });


        }catch(Exception e){
            Log.e("EXception:"+e,"uh oohh");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
