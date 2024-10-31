package com.kaizen.buildster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaizen.buildster.database.MyDBHelper;

import java.util.Locale;

public class ProjectDisplay extends AppCompatActivity {

    TextView proName;
    String projectName;
    int[] databaseResult;
    TextView sub_cost;
    TextView sup_cost;
    TextView roof_cost;
    TextView finish_cost;
    TextView sub_tv;
    TextView sup_tv;
    TextView roof_tv;
    TextView finish_tv;
    ProgressBar sub_progress;
    ProgressBar sup_progress;
    ProgressBar roof_progress;
    ProgressBar finish_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_display);

        proName = (TextView)findViewById(R.id.textView3);
        sub_cost = (TextView)findViewById(R.id.sub_cost);
        sup_cost = (TextView)findViewById(R.id.sup_cost);
        roof_cost = (TextView)findViewById(R.id.roof_cost);
        finish_cost = (TextView)findViewById(R.id.finish_cost);
        sub_tv = (TextView)findViewById(R.id.sub_tv);
        sup_tv = (TextView)findViewById(R.id.sup_tv);
        roof_tv = (TextView)findViewById(R.id.roof_tv);
        finish_tv = (TextView)findViewById(R.id.finish_tv);
        sub_progress = (ProgressBar)findViewById(R.id.sub_progress);
        sup_progress = (ProgressBar)findViewById(R.id.sup_progress);
        roof_progress = (ProgressBar)findViewById(R.id.roof_progress);
        finish_progress = (ProgressBar)findViewById(R.id.finish_progress);

        Bundle bundle = getIntent().getExtras();
        projectName = bundle.getString("name");
        proName.setText(projectName);

        MyDBHelper db = new MyDBHelper(getApplicationContext());
        databaseResult = db.getCardDetails(projectName);

        //assign values from database to card views
        sub_cost.setText(Integer.toString(databaseResult[0]));
        sup_cost.setText(Integer.toString(databaseResult[2]));
        roof_cost.setText(Integer.toString(databaseResult[4]));
        finish_cost.setText(Integer.toString(databaseResult[6]));
        sub_tv.setText(String.format(Locale.US,"%d", databaseResult[1]) + "%");
        sup_tv.setText(String.format(Locale.US,"%d", databaseResult[3]) + "%");
        roof_tv.setText(String.format(Locale.US,"%d", databaseResult[5]) + "%");
        finish_tv.setText(String.format(Locale.US,"%d", databaseResult[7]) + "%");
        sub_progress.setProgress(databaseResult[1]);
        sup_progress.setProgress(databaseResult[3]);
        roof_progress.setProgress(databaseResult[5]);
        finish_progress.setProgress(databaseResult[7]);
    }

    //create values for the cards
    public void onSubClick(View v){
        Intent i = new Intent(getApplicationContext(), SubActivity.class);
        i.putExtra("name", projectName);
        startActivity(i);
    }

    public void onSupClick(View v){
        Intent i = new Intent(getApplicationContext(), SupActivity.class);
        i.putExtra("name", projectName);
        startActivity(i);
    }

    public void onRoofClick(View v){
        Intent i = new Intent(getApplicationContext(), RoofActivity.class);
        i.putExtra("name", projectName);
        startActivity(i);
    }

    public void onFinishClick(View v){
        Intent i = new Intent(getApplicationContext(), FinishActivity.class);
        i.putExtra("name", projectName);
        startActivity(i);
    }

    public void takePic(View view){
        Toast.makeText(getApplicationContext(), "View not ready", Toast.LENGTH_LONG).show();
    }

    public void delete(View view){
        MyDBHelper db = new MyDBHelper(getApplicationContext());
        db.deleteProject(projectName);
        Intent intent = new Intent(ProjectDisplay.this, MainArea.class);
        startActivity(intent);
    }

    public void exit(View view){
        Intent intent = new Intent(ProjectDisplay.this, MainArea.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ProjectDisplay.this, MainArea.class);
        startActivity(intent);
    }
}
