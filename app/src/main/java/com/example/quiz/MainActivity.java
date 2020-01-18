package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btnContinue;
    private EditText Username;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";

    SharedPreferences sharedPreferences;

    RelativeLayout rl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Reference to all widgets
        btnContinue = findViewById(R.id.continueButton);
        Username    = findViewById(R.id.userName);
        rl  = findViewById(R.id.rl);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String val = sharedPreferences.getString(Name, null);


        if (val!= null){

            Username.setText(""+val);
            Intent i = new Intent(getApplicationContext(),Home.class);
            i.putExtra("Name", val);
            startActivity(i);
        }

        AnimationDrawable animationDrawable = (AnimationDrawable) rl.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(6000);
        animationDrawable.start();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Username.getText().toString().trim();

                if (name.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter name first",Toast.LENGTH_LONG).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, name);
                editor.commit();
                Intent i = new Intent(getApplicationContext(),Home.class);
                i.putExtra("Name", name);
                startActivity(i);
            }
        });
    }
}
