package com.kaizen.buildster.metric;

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

public class CircleMetric extends AppCompatActivity implements View.OnClickListener{

    int value;
    double[] result;
    String unit;
    String title;
    TextView userInput;
    String clear = "C";
    TextView value_areac;
    TextView unit_areac;
    TextView area;
    TextView diameter;
    TextView circumference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_metric);

        value_areac = (TextView)findViewById(R.id.value_areac);
        unit_areac = (TextView)findViewById(R.id.unit_areac);
        area = (TextView)findViewById(R.id.areaCircle);
        diameter = (TextView)findViewById(R.id.diameter);
        circumference = (TextView)findViewById(R.id.circumference);
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
        value = Integer.parseInt(value_areac.getText().toString());
        ConversionCalc c = new ConversionCalc();
        result = c.Circle(value, unit);
        area.setText(String.format(Locale.US, "%.2f", result[0]));
        diameter.setText(String.format(Locale.US, "%.2f", result[1]));
        circumference.setText(String.format(Locale.US, "%.2f", result[2]));
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
        value_areac.setText(title);
    }
}
