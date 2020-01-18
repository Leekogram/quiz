package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayersQuizLevels extends AppCompatActivity {




    private SharedPreferences mPrefs;
    private Button playerQuizLevel1;
    private Button playerQuizLevel2;
    private Button playerQuizLevel3;
    private ImageView back;

    String val;

    private SQLiteDatabase db;
    private Cursor c;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        mPrefs = getSharedPreferences("Quiz App",MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Name, null);


        val = mPrefs.getString("level", "n");

        playerQuizLevel1 = findViewById(R.id.playerQuizlevel1Button);
        playerQuizLevel2 = findViewById(R.id.playerQuizlevel2Button);
        playerQuizLevel3 = findViewById(R.id.playerQuizlevel3Button);
        back = findViewById(R.id.back);


        try {
            openDatabase();




            switch (val){

                case "level1":


                    playerQuizLevel2.setVisibility(View.VISIBLE);
                    playerQuizLevel3.setVisibility(View.INVISIBLE);

                    break;
                case  "level2":
                    playerQuizLevel2.setVisibility(View.VISIBLE);
                    playerQuizLevel3.setVisibility(View.VISIBLE);

                    break;

                case "level3":

                    playerQuizLevel2.setVisibility(View.VISIBLE);
                    playerQuizLevel3.setVisibility(View.VISIBLE);

                    break;
                case "level4":



                    playerQuizLevel2.setVisibility(View.VISIBLE);
                    playerQuizLevel3.setVisibility(View.VISIBLE);

                    break;
                default:
                    playerQuizLevel2.setVisibility(View.INVISIBLE);
                    playerQuizLevel3.setVisibility(View.INVISIBLE);

                    break;
            }



            /**Setting scores on buttons

        String lvl="level1";
        c = db.rawQuery("SELECT * FROM scores where level='"+lvl+"' and name='"+username+"'", null);

        if (!c.moveToFirst()) {
            playerQuizLevel1.setText("Level 1  0/10");

        } else {

            playerQuizLevel1.setText("Level 1  "+c.getString(2)+"/10");

        }


            String lvl2="level2";
            c = db.rawQuery("SELECT * FROM scores where level='"+lvl2+"' and name='"+username+"'", null);
           if (!c.moveToFirst()) {
                playerQuizLevel2.setText("Level 2  0/10");
            } else {

                playerQuizLevel2.setText("Level 2  "+c.getString(1)+"/ 10");
                playerQuizLevel3.setText("Level 3  0/10");
            }


            String lvl3="level3";
            c = db.rawQuery("SELECT * FROM scores where level='"+lvl3+"' and name='"+username+"'", null);
            if (!c.moveToFirst()) {
                playerQuizLevel3.setText("Level 3  0/10");
            } else {

                playerQuizLevel3.setText("Level 3  "+c.getString(1)+"/ 10");
                playerQuizLevel4.setText("Level 4  0/10");
            }


            String lvl4="level4";
           c = db.rawQuery("SELECT * FROM scores where level='"+lvl4+"' and name='"+username+"'", null);
            if (!c.moveToFirst()) {
                playerQuizLevel4.setText("Level 4  0/10");
            } else {

                playerQuizLevel4.setText("Level 4  "+c.getString(1)+"/ 10");
                playerQuizLevel5.setText("Level 5  0/10");
            }


            String lvl5="level5";
            c = db.rawQuery("SELECT * FROM scores where level='"+lvl5+"' and name='"+username+"'", null);
           if (!c.moveToFirst()) {
                playerQuizLevel5.setText("Level 5  0/10");
            } else {

                playerQuizLevel5.setText("Level 5  "+c.getString(1)+"/ 10");

            }**/




        playerQuizLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),playerQuizLevelInstructions.class);
                i.putExtra("level", "level1");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        playerQuizLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),playerQuizLevelInstructions.class);
                i.putExtra("level", "level2");
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        playerQuizLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),playerQuizLevelInstructions.class);
                i.putExtra("level", "level3");
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



    protected void openDatabase(){
        db = openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER, level VARCHAR)");
    }
}
