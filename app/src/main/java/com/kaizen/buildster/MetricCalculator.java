package com.kaizen.buildster;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.kaizen.buildster.metric.ArcMetric;
import com.kaizen.buildster.metric.CircleMetric;
import com.kaizen.buildster.metric.ConeMetric;
import com.kaizen.buildster.metric.CubeMetric;
import com.kaizen.buildster.metric.CylinderMetric;
import com.kaizen.buildster.metric.RectangleMetric;

public class MetricCalculator extends AppCompatActivity {

    private String[] ListArray = { "Arc", "Circle", "Rectangle", "Cone", "Cube", "Cylinder"};

    ListView listView;
    ArrayAdapter arrayAdapter;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    CoordinatorLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_calculator);

        initInstances();

        listView = (ListView) findViewById(R.id.list2);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListArray);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent newActivity = new Intent(view.getContext(), ArcMetric.class);
                    startActivity(newActivity);
                }
                else if (position == 1){
                    Intent newActivity = new Intent(view.getContext(), CircleMetric.class);
                    startActivity(newActivity);
                }
                else if (position == 2){
                    Intent newActivity = new Intent(view.getContext(), RectangleMetric.class);
                    startActivity(newActivity);
                }
                else if (position == 3){
                    Intent newActivity = new Intent(view.getContext(), ConeMetric.class);
                    startActivity(newActivity);
                }
                else if (position == 4){
                    Intent newActivity = new Intent(view.getContext(), CubeMetric.class);
                    startActivity(newActivity);
                }
                else if (position == 5){
                    Intent newActivity = new Intent(view.getContext(), CylinderMetric.class);
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
        mCollapsingToolbarLayout.setTitle("Metric Conversions");
        /****
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));
         ***/
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
