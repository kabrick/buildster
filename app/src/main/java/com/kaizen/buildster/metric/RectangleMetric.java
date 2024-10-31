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

public class RectangleMetric extends AppCompatActivity implements View.OnClickListener{

    TextView area, perimeter, recLength, recWidth, userInput;
    double[] result;
    int length, width;
    String title;
    String clear = "C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle_metric);

        recLength = (TextView)findViewById(R.id.recLength);
        recWidth = (TextView)findViewById(R.id.recWidth);
        area = (TextView)findViewById(R.id.recArea);
        perimeter = (TextView)findViewById(R.id.recPerimeter);
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

    public void valueClick2(View view){
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
                                valueCreate2();
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
        length = Integer.parseInt(recLength.getText().toString());
        width = Integer.parseInt(recWidth.getText().toString());
        ConversionCalc c = new ConversionCalc();
        result = c.Rectangle(length, width);
        area.setText(String.format(Locale.US, "%.2f", result[0]));
        perimeter.setText(String.format(Locale.US, "%.2f", result[1]));
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
        recLength.setText(title);
    }

    public void valueCreate2(){
        recWidth.setText(title);
    }
}
