package com.kaizen.buildster;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaizen.buildster.database.MyDBHelper;

public class SubActivity extends AppCompatActivity {

    private String[] ListArray = {"Cast Concrete (Foundation)", "Clay Blocks", "Hard Core", "Cast Concrete (Oversite)"};
    ListView listView;
    ArrayAdapter arrayAdapter;
    FloatingActionButton fab;
    int[] result;
    String projectName;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    String[] result2 = new String[6];

    String cement = "Cement (Bags)";
    String sand = "Sand (Tons)";
    String aggre = "Aggregates (Tons)";
    String clayBricks = "Clay Bricks";
    String hardcore = "HardCore (Tons)";
    String blocks = "Blocks";
    String nails = "Nails (Kgs)";
    String timber = "Sawn Cypress Timber";
    String steel = "12mm Steel Bars (Bars No.)";
    String rings = "8mm Rings";
    String bindingWire = "Binding Wire (Roll)";
    String ironSheets = "IronSheets";
    String boards = "Fascia Boards";
    String tiles = "Tiles (sm)";
    String metal = "Metal Lathing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Bundle bundle = getIntent().getExtras();
        projectName = bundle.getString("name");

        fab = (FloatingActionButton)findViewById(R.id.fab2);

        listView = (ListView) findViewById(R.id.list1);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListArray);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    method1();
                }
                else if (position == 1){
                    method2();
                }
                else if (position == 2){
                    method3();
                }
                else if (position == 3){
                    method4();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "View not ready", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void method1(){
        MyDBHelper db = new MyDBHelper(getApplication());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_display, null);

        result = db.getSubProjectDetails(projectName, 1);

        tv1 = (TextView)view.findViewById(R.id.tv1t);
        tv2 = (TextView)view.findViewById(R.id.tv2t);
        tv3 = (TextView)view.findViewById(R.id.tv3t);
        iv1 = (ImageView)view.findViewById(R.id.iv1t);
        iv2 = (ImageView)view.findViewById(R.id.iv2t);
        iv3 = (ImageView)view.findViewById(R.id.iv3t);
        t1 = (TextView)view.findViewById(R.id.t1t);
        t2 = (TextView)view.findViewById(R.id.t2t);
        t3 = (TextView)view.findViewById(R.id.t3t);

        //set text views
        tv1.setText(cement);
        tv2.setText(sand);
        tv3.setText(aggre);
        iv1.setImageResource(R.drawable.right);
        iv2.setImageResource(R.drawable.right);
        iv3.setImageResource(R.drawable.right);

        convertInt();

        t1.setText(result2[0]);
        t2.setText(result2[1]);
        t3.setText(result2[2]);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(projectName);

        alertDialogBuilder.setView(view);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Calculate",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(SubActivity.this, MaterialCalculator.class);
                                intent.putExtra("name", projectName);
                                intent.putExtra("item", 1);
                                intent.putExtra("activity", 1);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Dismiss",
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

    public void method3(){
        MyDBHelper db = new MyDBHelper(getApplication());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_display, null);

        result = db.getSubProjectDetails(projectName, 3);

        tv1 = (TextView)view.findViewById(R.id.tv1t);
        iv1 = (ImageView)view.findViewById(R.id.iv1t);
        t1 = (TextView)view.findViewById(R.id.t1t);

        //set text views
        tv1.setText(hardcore);
        iv1.setImageResource(R.drawable.right);

        convertInt();

        t1.setText(result2[0]);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(projectName);

        alertDialogBuilder.setView(view);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Calculate",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(SubActivity.this, MaterialCalculator.class);
                                intent.putExtra("name", projectName);
                                intent.putExtra("item", 3);
                                intent.putExtra("activity", 1);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Dismiss",
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

    public void method2(){
        MyDBHelper db = new MyDBHelper(getApplication());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_display, null);

        result = db.getSubProjectDetails(projectName, 2);

        tv1 = (TextView)view.findViewById(R.id.tv1t);
        tv2 = (TextView)view.findViewById(R.id.tv2t);
        tv3 = (TextView)view.findViewById(R.id.tv3t);
        iv1 = (ImageView)view.findViewById(R.id.iv1t);
        iv2 = (ImageView)view.findViewById(R.id.iv2t);
        iv3 = (ImageView)view.findViewById(R.id.iv3t);
        t1 = (TextView)view.findViewById(R.id.t1t);
        t2 = (TextView)view.findViewById(R.id.t2t);
        t3 = (TextView)view.findViewById(R.id.t3t);

        //set text views
        tv2.setText(cement);
        tv3.setText(sand);
        tv1.setText(blocks);
        iv1.setImageResource(R.drawable.right);
        iv2.setImageResource(R.drawable.right);
        iv3.setImageResource(R.drawable.right);

        convertInt();

        t1.setText(result2[0]);
        t2.setText(result2[1]);
        t3.setText(result2[2]);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(projectName);

        alertDialogBuilder.setView(view);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Calculate",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(SubActivity.this, MaterialCalculator.class);
                                intent.putExtra("name", projectName);
                                intent.putExtra("item", 2);
                                intent.putExtra("activity", 1);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Dismiss",
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

    public void method4(){
        MyDBHelper db = new MyDBHelper(getApplication());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_display, null);

        result = db.getSubProjectDetails(projectName, 4);

        tv1 = (TextView)view.findViewById(R.id.tv1t);
        tv2 = (TextView)view.findViewById(R.id.tv2t);
        tv3 = (TextView)view.findViewById(R.id.tv3t);
        iv1 = (ImageView)view.findViewById(R.id.iv1t);
        iv2 = (ImageView)view.findViewById(R.id.iv2t);
        iv3 = (ImageView)view.findViewById(R.id.iv3t);
        t1 = (TextView)view.findViewById(R.id.t1t);
        t2 = (TextView)view.findViewById(R.id.t2t);
        t3 = (TextView)view.findViewById(R.id.t3t);

        //set text views
        tv1.setText(cement);
        tv2.setText(sand);
        tv3.setText(aggre);
        iv1.setImageResource(R.drawable.right);
        iv2.setImageResource(R.drawable.right);
        iv3.setImageResource(R.drawable.right);

        convertInt();

        t1.setText(result2[0]);
        t2.setText(result2[1]);
        t3.setText(result2[2]);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(projectName);

        alertDialogBuilder.setView(view);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Calculate",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent intent = new Intent(SubActivity.this, MaterialCalculator.class);
                                intent.putExtra("name", projectName);
                                intent.putExtra("item", 4);
                                intent.putExtra("activity", 1);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Dismiss",
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

    public void convertInt(){
        int length = result.length;
        for (int i = 0; i < length; i++){
            result2[i] = Integer.toString(result[i]);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(SubActivity.this, ProjectDisplay.class);
        intent.putExtra("name", projectName);
        startActivity(intent);
    }
}
