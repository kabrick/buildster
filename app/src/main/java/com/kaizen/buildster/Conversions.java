package com.kaizen.buildster;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kaizen.buildster.conversions.AreaConversions;
import com.kaizen.buildster.conversions.DensityConversions;
import com.kaizen.buildster.conversions.EnergyConversions;
import com.kaizen.buildster.conversions.LengthConversion;
import com.kaizen.buildster.conversions.LiquidConversions;
import com.kaizen.buildster.conversions.PowerConversions;
import com.kaizen.buildster.conversions.SpeedConversions;
import com.kaizen.buildster.conversions.TemperatureConversions;
import com.kaizen.buildster.conversions.VolumeConversions;
import com.kaizen.buildster.conversions.WeightConversions;

public class Conversions extends AppCompatActivity {

    private String[] ListArray = { "Area", "Energy", "Density", "Power", "Liquid Volume", "Length", "Speed",
                                        "Temperature", "Weight", "Volume"};

    ListView listView;
    ArrayAdapter arrayAdapter;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    CoordinatorLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversions);

        initInstances();

        listView = (ListView) findViewById(R.id.list);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListArray);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent newActivity = new Intent(view.getContext(), AreaConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 1){
                    Intent newActivity = new Intent(view.getContext(), EnergyConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 2){
                    Intent newActivity = new Intent(view.getContext(), DensityConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 3){
                    Intent newActivity = new Intent(view.getContext(), PowerConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 4){
                    Intent newActivity = new Intent(view.getContext(), LiquidConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 5){
                    Intent newActivity = new Intent(view.getContext(), LengthConversion.class);
                    startActivity(newActivity);
                }
                else if (position == 6){
                    Intent newActivity = new Intent(view.getContext(), SpeedConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 7){
                    Intent newActivity = new Intent(view.getContext(), TemperatureConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 8){
                    Intent newActivity = new Intent(view.getContext(), WeightConversions.class);
                    startActivity(newActivity);
                }
                else if (position == 9){
                    Intent newActivity = new Intent(view.getContext(), VolumeConversions.class);
                    startActivity(newActivity);
                }
            }
        });
    }

    private void initInstances() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutAndroidExample);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRootLayout = (CoordinatorLayout) findViewById(R.id.coordinatorRootLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayoutAndroidExample);
        mCollapsingToolbarLayout.setTitle("Conversions");
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
