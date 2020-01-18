package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Highscores extends AppCompatActivity {

    private TextView txt2,txt3;

    private SQLiteDatabase db;
    private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        try {
            openDatabase();
            c = db.rawQuery("SELECT * FROM scores", null);
            if (!c.moveToFirst())
                txt2.setText("No high scores yet !");
            else {
                txt2.setText(c.getString(0));
                txt3.setText(c.getString(1));
            }

        }catch(Exception e){
            Log.e("EXception:"+e,"uh oohh");
        }

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_out_right,android.R.anim.slide_in_left);


    }

    protected void openDatabase(){
        db = openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
    }
}
