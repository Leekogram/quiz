package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {





    private Button startBtn,highscoreBtn,settingBtn,feedback,aboutBtn;
    private ImageButton shareBtn;
    private TextView user_name;
    String n;
    private MediaPlayer mediaPlayer ;

    private ConstraintLayout mainCont;

    private SharedPreferences mPrefs;


    Boolean val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mPrefs = getSharedPreferences("Quiz App2",MODE_PRIVATE);

        mediaPlayer  = MediaPlayer.create(getApplicationContext(),R.raw.gamemenu_tone);

        val = mPrefs.getBoolean("backgroundMusicStatus",true);


        startBtn = findViewById(R.id.startButton);
        highscoreBtn = findViewById(R.id.highScoreButton);
        settingBtn = findViewById(R.id.settingsButton);
        feedback = findViewById(R.id.contactButton);
        aboutBtn = findViewById(R.id.aboutButton);

        shareBtn = findViewById(R.id.shareButton);

        user_name = findViewById(R.id.userName);
        mainCont = findViewById(R.id.mainContainer);

        AnimationDrawable animationDrawable = (AnimationDrawable) mainCont.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(6000);
        animationDrawable.start();


        Bundle bundle = getIntent().getExtras();
        n = bundle.getString("Name");

        user_name.setText(n);



        //Listens for click on startButton
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,GameMode.class));

                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        highscoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Highscores.class));

                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        //Listens for click on settingsButton
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Settings.class));

                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                //Create a new Dialog_settings called dialog_highscores

            }
        });
        //Listens for click on feedBackButton
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent feedbackIntent = new Intent();
                feedbackIntent.setData(Uri.parse("mailto:abowabaoluwatobi@gmail.com"));
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL,"abowabaoluwatobi@gmail.com");
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from FootballQuiz App");

                try {
                    startActivity(Intent.createChooser(feedbackIntent, "send email via..."));
                }catch (android.content.ActivityNotFoundException ex){

                    Toast.makeText(Home.this,"No email clients/App Installed",Toast.LENGTH_LONG).show();
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out my app at: https://www.playstore.com");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Dialog_aboutapp called dialog_highscores
                Dialog_aboutapp dialog_aboutapp = new Dialog_aboutapp();

                // create the dialog
                dialog_aboutapp.show(getSupportFragmentManager(),"789");
            }
        });

    }
    

    @Override
    public  void onResume(){
        super.onResume();

        mPrefs = getSharedPreferences("Quiz App2",MODE_PRIVATE);

        mediaPlayer  = MediaPlayer.create(getApplicationContext(),R.raw.gamemenu_tone);

        val = mPrefs.getBoolean("backgroundMusicStatus",true);




        if (val == true) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } else {
                mediaPlayer.pause();
            }


    }
    @Override
    public  void onPause(){
        super.onPause();

        mediaPlayer.pause();
    }


}
