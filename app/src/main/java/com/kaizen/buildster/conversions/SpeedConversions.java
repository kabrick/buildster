package com.kaizen.buildster.conversions;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kaizen.buildster.ConversionCalc;
import com.kaizen.buildster.R;

import java.util.Locale;
import java.util.Objects;

public class SpeedConversions extends AppCompatActivity implements View.OnClickListener{

    int value;
    double[] result;
    String unit;
    String title;
    TextView userInput;
    String clear = "C";
    TextView value_area6;
    TextView unit_area6;
    TextView kh;
    TextView sound;
    TextView light;
    TextView knots;
    TextView miles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_conversions);

        value_area6 = (TextView)findViewById(R.id.value_area6);
        unit_area6 = (TextView)findViewById(R.id.unit_area6);
        sound = (TextView)findViewById(R.id.sound);
        light = (TextView)findViewById(R.id.light);
        knots = (TextView)findViewById(R.id.knots);
        kh = (TextView)findViewById(R.id.kh);
        miles = (TextView)findViewById(R.id.miles);
    }

    public void unitClick(View view){
        final String[] units = {"Kilometers/Hour", "Speed of sound", "Speed of light", "Knots", "Miles/Hour"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SpeedConversions.this);
        alertDialog.setTitle("Choose a unit to convert");
        alertDialog.setItems(units, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = units[which];
                unit = item;
                unit_area6.setText(unit);
            }
        });
        alertDialog.setNegativeButton("Cancel", null);
        alertDialog.show();
    }

    public void valueClick(View view){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view1 = layoutInflater.inflate(R.layout.value_input, null);

        view1.findViewById(R.id.b0).setOnClickListener(this);
        view1.findViewById(R.id.b1).setOnClickListener(this);
        view1.findViewById(R.id.b2).setOnClickListener(this);
        view1.findViewById(R.id.b3).setOnClickListener(this);
        view1.findViewById(R.id.b4).setOnClickListener(this);
        view1.findViewById(R.id.b5).setOnClickListener(this);
        view1.findViewById(R.id.b6).setOnClickListener(this);
        view1.findViewById(R.id.b7).setOnClickListener(this);
        view1.findViewById(R.id.b8).setOnClickListener(this);
        view1.findViewById(R.id.b9).setOnClickListener(this);
        view1.findViewById(R.id.dot).setOnClickListener(this);
        view1.findViewById(R.id.clear).setOnClickListener(this);

        userInput = (TextView) view1.findViewById(R.id.value);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Select units to convert");

        alertDialogBuilder.setView(view1);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                title = userInput.getText().toString();
                                valueCreate();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void calculateValues(View view){
        value = Integer.parseInt(value_area6.getText().toString());
        ConversionCalc c = new ConversionCalc();
        result = c.Speed(value, unit);
        kh.setText(String.format(Locale.US, "%.2f", result[0]));
        sound.setText(String.format(Locale.US, "%.2f", result[1]));
        light.setText(String.format(Locale.US, "%.2f", result[2]));
        knots.setText(String.format(Locale.US, "%.2f", result[3]));
        miles.setText(String.format(Locale.US, "%.2f", result[4]));
    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();
        if (Objects.equals(buttonPressed, clear)){
            userInput.setText("");
        }
        else {
            userInput.append(buttonPressed);
        }
    }

    public void valueCreate(){
        value_area6.setText(title);
    }
}
