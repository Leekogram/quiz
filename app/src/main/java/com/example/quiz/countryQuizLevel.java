package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class countryQuizLevel extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private Button countryQuizLevel1;
    private Button countryQuizLevel2;
    private Button countryQuizLevel3;
    private ImageView back;

    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz_level);

        try{

        mPrefs = getSharedPreferences("Quiz App",MODE_PRIVATE);


        val = mPrefs.getString("countryquizlevel", "n");

        countryQuizLevel1 = findViewById(R.id.countryQuizlevel1Button);
        countryQuizLevel2 = findViewById(R.id.countryQuizlevel2Button);
        countryQuizLevel3 = findViewById(R.id.countryQuizlevel3Button);
        back = findViewById(R.id.back);







        switch (val){
            case "countryquizlevel1":
                countryQuizLevel2.setVisibility(View.VISIBLE);
                countryQuizLevel3.setVisibility(View.INVISIBLE);

                break;
            case  "countryquizlevel2":
                countryQuizLevel2.setVisibility(View.VISIBLE);
                countryQuizLevel3.setVisibility(View.VISIBLE);

                break;
            case "countryquizlevel3":
                countryQuizLevel2.setVisibility(View.VISIBLE);
                countryQuizLevel3.setVisibility(View.VISIBLE);

                break;
            case "countryquizlevel4":
                countryQuizLevel2.setVisibility(View.VISIBLE);
                countryQuizLevel3.setVisibility(View.VISIBLE);

                break;
            default:
                countryQuizLevel2.setVisibility(View.INVISIBLE);
                countryQuizLevel3.setVisibility(View.INVISIBLE);

                break;
        }

        countryQuizLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),countryQuizInstructions.class);
                i.putExtra("level", "countryquizlevel1");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        countryQuizLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),countryQuizInstructions.class);
                i.putExtra("level", "countryquizlevel2");
                i.putExtra("question","How many times have this country won the African cup of nation(AFCON).");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        countryQuizLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),countryQuizInstructions.class);
                i.putExtra("level", "countryquizlevel3");
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
