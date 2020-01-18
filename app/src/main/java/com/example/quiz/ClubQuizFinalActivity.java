package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClubQuizFinalActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button nextbutton;
    int score;
    TextView scr;
    String bundleVal ;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_quiz_final);


        mPrefs = getSharedPreferences("Quiz App",MODE_PRIVATE);
        mEditor = mPrefs.edit();

        linearLayout = findViewById(R.id.stars);
        scr = findViewById(R.id.score);
        final Animation shake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        linearLayout.startAnimation(shake);

        nextbutton = findViewById(R.id.nextButton);

        Bundle data = getIntent().getExtras();

        score = data.getInt("score");
        bundleVal =data.getString("clubquizlevel");

        scr.setText(""+score+"/ 10");


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putString("clubquizlevel", bundleVal);
                mEditor.commit();
                Intent i = new Intent(ClubQuizFinalActivity.this, ClubQuizLevels.class);
                startActivity(i);
            }
        });
    }
}
