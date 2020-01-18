package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    Boolean backgroundMusic,soundFxTone;
    Boolean val, val2;

    CheckBox checkBoxBackMusic ;
    CheckBox checkBoxSurfixSound;
    private MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPrefs = getSharedPreferences("Quiz App2",MODE_PRIVATE);
        mEditor = mPrefs.edit();

        mediaPlayer  = MediaPlayer.create(getApplicationContext(),R.raw.gamemenu_tone);

        checkBoxBackMusic = findViewById(R.id.backgroundMusic);
        checkBoxSurfixSound = findViewById(R.id.surfixSound);
        final Button cancelButton = findViewById(R.id.cancelBtn);
        final Button saveButton = findViewById(R.id.saveBtn);

         val = mPrefs.getBoolean("backgroundMusicStatus",true);
        val2 = mPrefs.getBoolean("soundFxStatus",true);

         if (val == true){
             checkBoxBackMusic.setChecked(true);

         }else {
             checkBoxBackMusic.setChecked(false);
         }

        if (val2 == true){
            checkBoxSurfixSound.setChecked(true);

        }else {
            checkBoxSurfixSound.setChecked(false);
        }






        checkBoxBackMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
                    mEditor.remove("backgroundMusicStatus");
                    backgroundMusic = true;
                    mEditor.putBoolean("backgroundMusicStatus", backgroundMusic);

                }else{
                    mEditor.remove("backgroundMusicStatus");
                    backgroundMusic = false;
                    mEditor.putBoolean("backgroundMusicStatus", backgroundMusic);

                }

            }
        });

         checkBoxSurfixSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if (isChecked){
                     mEditor.remove("soundFxStatus");
                     soundFxTone = true;
                     mEditor.putBoolean("soundFxStatus", soundFxTone);

                 }else{
                     mEditor.remove("soundFxStatus");
                     soundFxTone = false;
                     mEditor.putBoolean("soundFxStatus", soundFxTone);

                 }

             }
         });


        //Handles the cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this,"Cancelled !!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.commit();
                Toast.makeText(Settings.this,"Saved !!!", Toast.LENGTH_LONG).show();
                // finish();
               // Intent i = new Intent(Settings.this,Home.class);
                //startActivity(i);


            }
        });
    }



    @Override
    public  void onResume(){
        super.onResume();

        if (val == true){
            checkBoxBackMusic.setChecked(true);

        }else {
            checkBoxBackMusic.setChecked(false);
        }

        if (val2 == true){
            checkBoxSurfixSound.setChecked(true);

        }else {
            checkBoxSurfixSound.setChecked(false);
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
