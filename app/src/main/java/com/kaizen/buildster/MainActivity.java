package com.kaizen.buildster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kaizen.buildster.database.MyDBHelper;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.kaizen.buildster", MODE_PRIVATE);

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 3000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }
                    Intent intent = new Intent(getApplicationContext(), MainArea.class);
                    startActivity(intent);
                }

                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();
    }

    @Override
    protected void onResume(){
        super.onResume();

        String result;

        if (prefs.getBoolean("firstrun", true)){
            MyDBHelper dbHelper = new MyDBHelper(this);
            result = dbHelper.createProjectList();
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            prefs.edit().putBoolean("firstrun", false).apply();
        }
        else {
            result = "This is not the first launch";
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }
}
