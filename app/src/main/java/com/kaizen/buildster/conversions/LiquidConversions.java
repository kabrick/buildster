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

public class LiquidConversions extends AppCompatActivity implements View.OnClickListener{

    int value;
    double[] result;
    String unit;
    String title;
    TextView userInput;
    String clear = "C";
    TextView value_area5;
    TextView unit_area5;
    TextView gallon;
    TextView liters;
    TextView ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid_conversions);

        value_area5 = (TextView)findViewById(R.id.value_area5);
        unit_area5 = (TextView)findViewById(R.id.unit_area5);
        gallon = (TextView)findViewById(R.id.gallon);
        liters = (TextView)findViewById(R.id.liters);
        ml = (TextView)findViewById(R.id.ml);
    }

    public void unitClick(View view){
        final String[] units = {"Gallons", "Liters", "Milliliters"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LiquidConversions.this);
        alertDialog.setTitle("Choose a unit to convert");
        alertDialog.setItems(units, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = units[which];
                unit = item;
                unit_area5.setText(unit);
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
        value = Integer.parseInt(value_area5.getText().toString());
        ConversionCalc c = new ConversionCalc();
        result = c.LiquidVolume(value, unit);
        gallon.setText(String.format(Locale.US, "%.2f", result[0]));
        liters.setText(String.format(Locale.US, "%.2f", result[1]));
        ml.setText(String.format(Locale.US, "%.2f", result[2]));
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
        value_area5.setText(title);
    }
}

