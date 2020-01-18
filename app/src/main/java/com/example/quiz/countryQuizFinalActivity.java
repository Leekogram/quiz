package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class countryQuizFinalActivity extends AppCompatActivity {


    LinearLayout linearLayout;
    Button nextbutton;
    int score;
    TextView scr;
    String bundleVal ;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;

    private SQLiteDatabase db;
    private Cursor c;
    private static  final String x = "SELECT * FROM scores";
    String query,username;

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz_final);

        try {
            sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            username = sharedPreferences.getString(Name, null);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.level_completion_tone);
            mediaPlayer.start();

            mPrefs = getSharedPreferences("Quiz App", MODE_PRIVATE);
            mEditor = mPrefs.edit();
            createdatabase();

            linearLayout = findViewById(R.id.stars);
            scr = findViewById(R.id.score);
            final Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
            linearLayout.startAnimation(shake);

            nextbutton = findViewById(R.id.nextButton);

            Bundle data = getIntent().getExtras();

            score = data.getInt("score");
            bundleVal = data.getString("level");

            scr.setText("" + score + "/ 10");


            nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c = db.rawQuery("SELECT * FROM scores where level='" + "level1" + "' and name='" + username + "'", null);
                    if (!c.moveToFirst()) {
                        query = "INSERT INTO scores VALUES('" + username + "','" + score + "', '" + bundleVal + "')";
                        db.execSQL(query);
                        Toast.makeText(countryQuizFinalActivity.this, "You have got a high score", Toast.LENGTH_LONG).show();
                    } else {
                        c.moveToLast();
                        if (score > Integer.parseInt(c.getString(1))) {
                            query = "UPDATE scores SET name='" + username + "', score='" + score + "', leve='" + bundleVal + "' where score='" + c.getString(1) + "' and name='" + username + "' ";
                            db.execSQL(query);
                            Toast.makeText(countryQuizFinalActivity.this, "You have got a high score", Toast.LENGTH_LONG).show();
                        }
                    }
                    mEditor.putString("countryquizlevel", bundleVal);
                    mEditor.commit();
                    Intent i = new Intent(countryQuizFinalActivity.this, countryQuizLevel.class);
                    startActivity(i);
                }
            });
        }catch (Exception e){
            Log.e("Exception:"+e,"uh oohh");
        }
    }

    @Override
    public void onBackPressed(){
        // super.onBackPressed();
        // overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
    protected void createdatabase(){
        db = openOrCreateDatabase("ScoresDB.db",Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER, level VARCHAR)");
    }
}
