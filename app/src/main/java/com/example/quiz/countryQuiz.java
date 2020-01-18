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

public class countryQuiz extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;

    private TextView countLabel,timeLabel;
    private ImageView questionImage;
    private Button answerBtn1,answerBtn2,answerBtn3,answerBtn4;
    private ImageView back;

    private MediaPlayer mediaPlayer,mediaPlayer2,mediaPlayer3 ;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    CountDownTimer timer;
    String bundleVal,bundleVal2;
    private TextView questionTxt;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    Boolean val1, val2;

    private SQLiteDatabase db;
    private Cursor c;
    private static  final String x = "SELECT * FROM scores";

    String query,username;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {
            //{"questionimage","Right Answer","option1","option2","option3"}
            {"nigeria","Nigeria","Niger","Rwanda","Kenya"},
            {"ghana","Ghana","Togo","Cameroon","Sudan"},
            {"algeria","Algeria","Nigeria","Somalia","Tunisia"},
            {"egypt","Egypt","South Africa","Ghana","Tanzania"},
            {"cameroon","Cameroon","Uganda","Morocco","Angola"},
            {"tunisia","Tunisia","Burundi","Libya","Sierra Leone"},
            {"south_africa","South Africa","Liberia","Namibia","Gambia"},
            {"togo","Togo","Benin","Chad","Zimbabwe"},
            {"cotedivoire","Cote d'Ivoire","Magagascar","Mozambique","Ethiopia"},
            {"senegal","Senegal","Malawi","Congo","Botswana"}
    };
    String quizData2[][] = {
            {"nigeria","3","4","2","1"},
            {"ghana","4","5","10","9"},
            {"algeria","2","1","5","3"},
            {"egypt","7","1","6","5"},
            {"cameroon","5","4","3","2"},
            {"tunisia","1","2","3","4"},
            {"south_africa","1","0","2","4"},
            {"togo","0","1","5","4"},
            {"cotedivoire","2","3","4","0"},
            {"senegal","0","1","2","3"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_quiz);

        try {

            mPrefs = getSharedPreferences("Quiz App2",MODE_PRIVATE);
            mEditor = mPrefs.edit();
            sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String val = sharedPreferences.getString(Name, null);

            val1 = mPrefs.getBoolean("backgroundMusicStatus",true);
            val2 = mPrefs.getBoolean("soundFxStatus",true);





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


            countLabel = findViewById(R.id.countLabel);
            timeLabel = findViewById(R.id.timerLabel);
            questionImage = findViewById(R.id.questionImage);
            answerBtn1 = findViewById(R.id.answerBtn1);
            answerBtn2 = findViewById(R.id.answerBtn2);
            answerBtn3 = findViewById(R.id.answerBtn3);
            answerBtn4 = findViewById(R.id.answerBtn4);
            questionTxt = findViewById(R.id.question);
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
            bundleVal2 = data.getString("question");

            if (bundleVal.equals("countryquizlevel1")) {




                //Create quizArray from quizData
                for (int i = 0; i < quizData.length; i++) {
                    //Prepare array
                    ArrayList<String> tmpArray = new ArrayList<>();
                    tmpArray.add(quizData[i][0]); //Image Name
                    tmpArray.add(quizData[i][1]); //Right Answer
                    tmpArray.add(quizData[i][2]); // Option1
                    tmpArray.add(quizData[i][3]); // Option2
                    tmpArray.add(quizData[i][4]); // Option3

                    //Add tmpArray to quizArray.
                    quizArray.add(tmpArray);
                }




            }
            if (bundleVal.equals("countryquizlevel2")) {
                //Create quizArray from quizData

                questionTxt.setVisibility(View.VISIBLE);
                questionTxt.setText(bundleVal2);
                for (int i = 0; i < quizData2.length; i++) {
                    //Prepare array
                    ArrayList<String> tmpArray = new ArrayList<>();
                    tmpArray.add(quizData2[i][0]); //Image Name
                    tmpArray.add(quizData2[i][1]); //Right Answer
                    tmpArray.add(quizData2[i][2]); // Option1
                    tmpArray.add(quizData2[i][3]); // Option2
                    tmpArray.add(quizData2[i][4]); // Option3

                    //Add tmpArray to quizArray.
                    quizArray.add(tmpArray);
                }

            }
            showNextQuiz();
        }catch (Exception e){

            Log.e("EXception:"+e,"uh oohh");
        }
    }

    public void showNextQuiz(){
        //update quizCountLabel.

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
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view){

        //Get tabbed Button
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)){
            //Correct

            alertTitle = "Correct!";
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

        if (!countryQuiz.this.isFinishing()) {
//Create Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("Answer: " + rightAnswer);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    if (quizArray.size() < 1) {
                        //quizArray is empty.
                        showResult();
                    } else {
                        quizCount++;
                        timer.cancel();
                        startCount();
                        showNextQuiz();
                    }


                }
            });
            builder.setCancelable(false);
            builder.show();
        }
    }

    public void showResult() {

        if (rightAnswerCount >= 10) {

            Toast.makeText(getApplicationContext(),"You have completed the quiz",Toast.LENGTH_LONG).show();
            timer.cancel();
            Intent i = new Intent(countryQuiz.this, countryQuizFinalActivity.class);
            i.putExtra("score", rightAnswerCount);
            i.putExtra("level",bundleVal);
            startActivity(i);

        } else {
            if (!countryQuiz.this.isFinishing()) {
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
                            Toast.makeText(countryQuiz.this, "You have got a high score", Toast.LENGTH_LONG).show();
                        } else {
                            c.moveToLast();
                            if (rightAnswerCount > Integer.parseInt(c.getString(1))) {
                                query = "UPDATE scores SET name='" + username + "', score='" + rightAnswerCount + "', leve='" + bundleVal + "' where score='" + c.getString(1) + "' and name='" + username + "'";
                                db.execSQL(query);
                                Toast.makeText(countryQuiz.this, "You have got a high score", Toast.LENGTH_LONG).show();
                            }
                        }


                        mEditor.putString("level", bundleVal);
                        mEditor.commit();
                        Intent i = new Intent(countryQuiz.this, countryQuizLevel.class);
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
