package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayerQuizLevel3 extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;

    private TextView countLabel,timeLabel,userAnswer;
    private ImageView questionImage;
    private Button continueBtn;
    private ImageView back;

    private MediaPlayer mediaPlayer,mediaPlayer2,mediaPlayer3 ;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    CountDownTimer timer;
    String bundleVal;


    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    Boolean val1, val2;

    private SQLiteDatabase db;
    private Cursor c;
    private static  final String x = "SELECT * FROM scores";

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String query,username;

    String quizData3[][] = {
            //{"questionimage","Right Answer","option1","option2","option3"}
            {"messi_image","Jersey 10","Jersey 9","Jersey 11","Jersey 2"},
            {"neymar_image","Neymar Jr","Philippe Coutinho","Luiz Suarez","Anthony Martial"},
            {"ronaldo_image","Christiano Ronaldo","Karim Benzema","Ashley Young","Gareth Bale"},
            {"pogba_image","Paul Pogba","Lucas Modric","Romelu Lukaku","Anthony Martial"},
            {"rashford_image","Marcus Rashford","Jesse Lingard","Luke Shaw","ALverez Morata"},
            {"silva_image","David silva","Juan Mata","Leroy Sane","Raheem Sterling"},
            {"salah_image","Mohamed Salah","Sadio Mane","Virgil van Dijk","Divock Origi"},
            {"suarez_image","Liuz Suarez","Antoine Griezmann","Arturo Vidal","Ivan Rakitic"},
            {"bale_image","Gareth Bale","James Rodriguez","Luka Jovic","Eden Hazard"},
            {"ibrahimovic_image","Zlatan Ibrahimovic","Edinson Cavani","Angel Di Maria","Thiago Silva"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_quiz_level3);

        try {

            mPrefs = getSharedPreferences("Quiz App2", MODE_PRIVATE);
            mEditor = mPrefs.edit();
            sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String val = sharedPreferences.getString(Name, null);

            val1 = mPrefs.getBoolean("backgroundMusicStatus", true);
            val2 = mPrefs.getBoolean("soundFxStatus", true);


            if (val != null) {

                username = val;
                /** Intent i = new Intent(getApplicationContext(),Home.class);
                 i.putExtra("Name", val);
                 startActivity(i);**/
            }
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.gamestart_tone);

            if (val1 == true) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            } else {
                mediaPlayer.pause();
            }


            userAnswer = findViewById(R.id.userAnswer);
            countLabel = findViewById(R.id.countLabel);
            timeLabel = findViewById(R.id.timerLabel);
            questionImage = findViewById(R.id.questionImage);
            continueBtn = findViewById(R.id.continueButton);
            back = findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            startCount();
            createdatabase();


            Bundle data = getIntent().getExtras();
            bundleVal = data.getString("level");


            if (bundleVal.equals("level3")) {
                //Create quizArray from quizData
                for (int i = 0; i < quizData3.length; i++) {
                    //Prepare array
                    ArrayList<String> tmpArray = new ArrayList<>();
                    tmpArray.add(quizData3[i][0]); //Image Name
                    tmpArray.add(quizData3[i][1]); //Right Answer
                    tmpArray.add(quizData3[i][2]); // Option1
                    tmpArray.add(quizData3[i][3]); // Option2
                    tmpArray.add(quizData3[i][4]); // Option3

                    //Add tmpArray to quizArray.

                    quizArray.add(tmpArray);


                }
                showNextQuiz();
            }
            }catch (Exception e){

                Log.e("EXception:"+e,"uh oohh");
            }
            }

    public void showNextQuiz(){
        //update quizCountLabel.

        userAnswer.setText("");

        countLabel.setText("Question :" + quizCount +"/ 10");

        //Generate random number between 0 and array length
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set Image and Right Answer

        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0),"drawable", getPackageName())
        );
        rightAnswer = quiz.get(1);

        //Remove "Image name" from quiz and shuffle options

        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set options
        //answerBtn1.setText(quiz.get(0));
        //answerBtn2.setText(quiz.get(1));
        //answerBtn3.setText(quiz.get(2));
        //answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view){

        //Get tabbed Button
       // Button answerBtn = findViewById(view.getId());
        String answerText = userAnswer.getText().toString().trim();

        String alertTitle;

        if (answerText.equalsIgnoreCase(rightAnswer)){
            //Correct

          //  alertTitle = "Correct!";
            rightAnswerCount++;
            mediaPlayer3  = MediaPlayer.create(getApplicationContext(),R.raw.winning_tone);

            if (val2 == true) {
                mediaPlayer3.start();

            } else {
                mediaPlayer3.pause();
            }
        }else {
            //Wrong
            alertTitle = "Wrong...";
            mediaPlayer2  = MediaPlayer.create(getApplicationContext(),R.raw.alert2);

            if (val2 == true) {
                mediaPlayer2.start();

            } else {
                mediaPlayer2.pause();
            }

        }



        if (quizArray.size() < 1){
            //quizArray is empty.
            showResult();
        }else {
            quizCount++;
            timer.cancel();
            startCount();
            showNextQuiz();
        }



    }

    public void showResult() {

        if (rightAnswerCount >= 10) {

            Toast.makeText(getApplicationContext(),"You have completed the quiz",Toast.LENGTH_LONG).show();
            timer.cancel();
            Intent i = new Intent(PlayerQuizLevel3.this, PlayersQuizFinalActivity.class);
            i.putExtra("score", rightAnswerCount);
            i.putExtra("level",bundleVal);
            startActivity(i);

        } else {
            if (!PlayerQuizLevel3.this.isFinishing()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Result");
                builder.setMessage(rightAnswerCount + "/ 10");
                builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        recreate();
                    }
                });
                builder.setNegativeButton("Save & Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c = db.rawQuery(x, null);
                        if (!c.moveToFirst()) {
                            query = "INSERT INTO scores VALUES('" + username + "','" + rightAnswerCount + "', '" + bundleVal + "')";
                            db.execSQL(query);
                            Toast.makeText(PlayerQuizLevel3.this, "You have got a high score", Toast.LENGTH_LONG).show();
                        } else {
                            c.moveToLast();
                            if (rightAnswerCount > Integer.parseInt(c.getString(1))) {
                                query = "UPDATE scores SET name='" + username + "', score='" + rightAnswerCount + "', leve='" + bundleVal + "' where score='" + c.getString(1) + "' and name='" + username + "'";
                                db.execSQL(query);
                                Toast.makeText(PlayerQuizLevel3.this, "You have got a high score", Toast.LENGTH_LONG).show();
                            }
                        }


                        mEditor.putString("level", bundleVal);
                        mEditor.commit();
                        Intent i = new Intent(PlayerQuizLevel3.this, PlayersQuizLevels.class);
                        startActivity(i);
                        finish();
                    }
                });
                builder.show();
            }
        }
    }

    protected void startCount(){

        timer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLabel.setText(""+millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timeLabel.setText("0");
                showResult();
            }
        }.start();
    }

    @Override
    public  void onPause(){
        super.onPause();

        mediaPlayer.pause();
    }
    protected void createdatabase(){
        db = openOrCreateDatabase("ScoresDB.db",Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores( name VARCHAR, score NUMBER, level VARCHAR)");
    }

}
