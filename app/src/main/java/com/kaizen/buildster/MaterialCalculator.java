package com.kaizen.buildster;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaizen.buildster.conversions.AreaConversions;
import com.kaizen.buildster.database.MyDBHelper;

import java.util.Locale;
import java.util.Objects;

public class MaterialCalculator extends AppCompatActivity implements View.OnClickListener {

    String item_desc;
    String result2[] = new String[6];
    int result[];
    String quantity_string;
    int quantity_integer;
    TextView userInput;
    String clear = "C";

    TextView item_desc2;
    TextView quantity;
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

    int ifFromDatabase = 0;
    String projectName;
    int item;
    int activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calculator);

        // test to see if activity is started by intent or not
        try{
            Bundle bundle = getIntent().getExtras();
            projectName = bundle.getString("name");
            item = bundle.getInt("item");
            activity = bundle.getInt("activity");
            ifFromDatabase = 1;
        }catch (Exception e){
            //if activity is not started by intent
        }

        item_desc2 = (TextView)findViewById(R.id.item_desc);
        quantity = (TextView)findViewById(R.id.quantity);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        iv3 = (ImageView)findViewById(R.id.iv3);
        iv4 = (ImageView)findViewById(R.id.iv4);
        iv5 = (ImageView)findViewById(R.id.iv5);
        iv6 = (ImageView)findViewById(R.id.iv6);
        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t3 = (TextView)findViewById(R.id.t3);
        t4 = (TextView)findViewById(R.id.t4);
        t5 = (TextView)findViewById(R.id.t5);
        t6 = (TextView)findViewById(R.id.t6);
    }

    public void unitClicks(View view){
        final String[] units = {"Cast Concrete(foundation)", "Clay Blocks(substructure)", "Hardcore with blinding for cast concrete(250mm)",
                "Cast oversite concrete", "Clay blocks(superstructure)", "Kirundu formwork", "Cast concrete(reinforced superstructure)",
                "Sawn cypress(150x50)", "Sawn cypress(100x75)", "Sawn cypress(100x50)", "Roofing sheets(1000x3000mm)", "Fascia boards",
                "Floor finish(25mm cement)", "Tiles(floor)", "Tiles(wall)", "Metal lathing with eaves", "Timber joists framework",
                "Ceiling(cement sand 25mm)", "Plaster(cement sand 15mm)", "Rendering(cement sand 15mm)"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MaterialCalculator.this);
        alertDialog.setTitle("Choose a unit to convert");
        alertDialog.setItems(units, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item = units[which];
                item_desc = item;
                item_desc2.setText(item_desc);
                updateTextViews();
            }
        });
        alertDialog.setNegativeButton("Cancel", null);
        alertDialog.show();
    }

    public void valueClicks(View view){
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
                                quantity_string = userInput.getText().toString();
                                quantity.setText(quantity_string);
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

    public void calculateButton(View view){
        quantity_integer = Integer.parseInt(quantity.getText().toString());
        Calculations cal = new Calculations();
        switch (item_desc) {
            case "Cast Concrete(foundation)":
                result = cal.CastCon(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                t3.setText(result2[2]);
                break;
            case "Clay Blocks(substructure)":
                result = cal.ClayBlo(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                t3.setText(result2[2]);
                break;
            case "Hardcore with blinding for cast concrete(250mm)":
                result = cal.HardCore(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Cast oversite concrete":
                result = cal.CastOver(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                t3.setText(result2[2]);
                break;
            case "Clay blocks(superstructure)":
                result = cal.ClayBloc(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                t3.setText(result2[2]);
                break;
            case "Kirundu formwork":
                result = cal.KirForm(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Cast concrete(reinforced superstructure)":
                result = cal.CastConc(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                t3.setText(result2[2]);
                t4.setText(result2[3]);
                t5.setText(result2[4]);
                t6.setText(result2[5]);
                break;
            case "Sawn cypress(150x50)":
                result = cal.SawnCy(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Sawn cypress(100x75)":
                result = cal.SawnCyp(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Sawn cypress(100x50)":
                result = cal.SawnCypr(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Roofing sheets(1000x3000mm)":
                result = cal.RoofingSheets(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Fascia boards":
                result = cal.FasciaBoards(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Floor finish(25mm cement)":
                result = cal.FloorFinish(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Tiles(floor)":
                result = cal.TilesFloor(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Tiles(wall)":
                result = cal.TilesWalls(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Metal lathing with eaves":
                result = cal.MetalEaves(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Timber joists framework":
                result = cal.TimberJoi(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                break;
            case "Ceiling(cement sand 25mm)":
                result = cal.Ceiling(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Plaster(cement sand 15mm)":
                result = cal.Plaster(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            case "Rendering(cement sand 15mm)":
                result = cal.Rendering(quantity_integer);
                convertInt();
                t1.setText(result2[0]);
                t2.setText(result2[1]);
                break;
            default:

                break;
        }
    }

    public void convertInt(){
        int length = result.length;
        for (int i = 0; i < length; i++){
            result2[i] = Integer.toString(result[i]);
        }
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

    public void updateTextViews(){
        switch (item_desc) {
            case "Cast Concrete(foundation)":
                tv1.setText(cement);
                tv2.setText(sand);
                tv3.setText(aggre);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                iv3.setImageResource(R.drawable.right);
                break;
            case "Clay Blocks(substructure)":
                tv1.setText(clayBricks);
                tv2.setText(cement);
                tv3.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                iv3.setImageResource(R.drawable.right);
                break;
            case "Hardcore with blinding for cast concrete(250mm)":
                tv1.setText(hardcore);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Cast oversite concrete":
                tv1.setText(cement);
                tv2.setText(sand);
                tv3.setText(aggre);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                iv3.setImageResource(R.drawable.right);
                break;
            case "Clay blocks(superstructure)":
                tv1.setText(blocks);
                tv2.setText(cement);
                tv3.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                iv3.setImageResource(R.drawable.right);
                break;
            case "Kirundu formwork":
                tv1.setText(nails);
                tv2.setText(timber);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Cast concrete(reinforced superstructure)":
                tv1.setText(cement);
                tv2.setText(sand);
                tv3.setText(aggre);
                tv4.setText(steel);
                tv5.setText(rings);
                tv6.setText(bindingWire);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                iv3.setImageResource(R.drawable.right);
                iv4.setImageResource(R.drawable.right);
                iv5.setImageResource(R.drawable.right);
                iv6.setImageResource(R.drawable.right);
                break;
            case "Sawn cypress(150x50)":
                tv1.setText(timber);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Sawn cypress(100x75)":
                tv1.setText(timber);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Sawn cypress(100x50)":
                tv1.setText(timber);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Roofing sheets(1000x3000mm)":
                tv1.setText(ironSheets);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Fascia boards":
                tv1.setText(boards);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Floor finish(25mm cement)":
                tv1.setText(cement);
                tv2.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Tiles(floor)":
                tv1.setText(tiles);
                tv2.setText(cement);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Tiles(wall)":
                tv1.setText(tiles);
                tv2.setText(cement);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Metal lathing with eaves":
                tv1.setText(metal);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Timber joists framework":
                tv1.setText(timber);
                iv1.setImageResource(R.drawable.right);
                break;
            case "Ceiling(cement sand 25mm)":
                tv1.setText(cement);
                tv2.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Plaster(cement sand 15mm)":
                tv1.setText(cement);
                tv2.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            case "Rendering(cement sand 15mm)":
                tv1.setText(cement);
                tv2.setText(sand);
                iv1.setImageResource(R.drawable.right);
                iv2.setImageResource(R.drawable.right);
                break;
            default:

                break;
        }
    }

    public void save(View view){
        if (ifFromDatabase == 1) {

            MyDBHelper db = new MyDBHelper(getApplicationContext());
            // save all values to database and go back
            switch (activity){
                case 1:
                    db.updateSubData(projectName, result, item);
                    break;
                case 2:
                    db.updateSupData(projectName, result, item);
                    break;
                case 3:
                    db.updateRoofData(projectName, result, item);
                    break;
                case 4:
                    db.updateFinData(projectName, result, item);
                    break;
            }

            goToBaseActivity();
        }
        else {
            Toast.makeText(getApplicationContext(), "You don't have any open projects", Toast.LENGTH_LONG).show();
        }
    }

    public void discard(View view){
        if (ifFromDatabase == 1) {
            goToBaseActivity();
        }
        else {
            Toast.makeText(getApplicationContext(), "You don't have any open projects", Toast.LENGTH_LONG).show();
        }
    }

    void goToBaseActivity(){
        Intent intent;
        switch (activity){
            case 1:
                intent = new Intent(MaterialCalculator.this, SubActivity.class);
                intent.putExtra("name", projectName);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MaterialCalculator.this, SupActivity.class);
                intent.putExtra("name", projectName);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(MaterialCalculator.this, RoofActivity.class);
                intent.putExtra("name", projectName);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(MaterialCalculator.this, FinishActivity.class);
                intent.putExtra("name", projectName);
                startActivity(intent);
                break;
        }
    }
}
