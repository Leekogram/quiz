package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClubQuizLevels extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private Button playerQuizLevel1;
    private Button playerQuizLevel2;
    private Button playerQuizLevel3;
    private ImageView back;

    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_quiz_levels);

        mPrefs = getSharedPreferences("Quiz App",MODE_PRIVATE);


        val = mPrefs.getString("clubquizlevel", "n");

        playerQuizLevel1 = findViewById(R.id.playerQuizlevel1Button);
        playerQuizLevel2 = findViewById(R.id.playerQuizlevel2Button);
        playerQuizLevel3 = findViewById(R.id.playerQuizlevel3Button);
        back = findViewById(R.id.back);







        switch (val){
            case "clubquizlevel1":
                playerQuizLevel2.setVisibility(View.VISIBLE);
                playerQuizLevel3.setVisibility(View.INVISIBLE);

                break;
            case  "clubquizlevel2":
                playerQuizLevel2.setVisibility(View.VISIBLE);
                playerQuizLevel3.setVisibility(View.VISIBLE);

                break;
            case "clubquizlevel3":
                playerQuizLevel2.setVisibility(View.VISIBLE);
                playerQuizLevel3.setVisibility(View.VISIBLE);

                break;
            case "clubquizlevel4":
                playerQuizLevel2.setVisibility(View.VISIBLE);
                playerQuizLevel3.setVisibility(View.VISIBLE);

                break;
            default:
                playerQuizLevel2.setVisibility(View.INVISIBLE);
                playerQuizLevel3.setVisibility(View.INVISIBLE);

                break;
        }




        playerQuizLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),ClubQuiz.class);
                i.putExtra("clubquizlevel", "clubquizlevel1");
                i.putExtra("question","Identify the player does not belong to this club ?");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        playerQuizLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ClubQuiz.class);
                i.putExtra("clubquizlevel", "clubquizlevel2");
                i.putExtra("question","Identify the club formation");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        playerQuizLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ClubQuizLevel3.class);
                i.putExtra("clubquizlevel", "clubquizlevel3");
                i.putExtra("question","Identify the club manager ");
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
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
