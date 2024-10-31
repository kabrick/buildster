package com.kaizen.buildster;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CalculationsFragment extends ListFragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[]{
                "Conversions", "Metric Calculation", "Basic Calculator", "Material Calculator"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_placeholder, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView i, View v, int position, long id){
        super.onListItemClick(i, v, position, id);
        if (position == 0){
            Intent newActivity = new Intent(v.getContext(), Conversions.class);
            startActivity(newActivity);
        }
        else if (position == 1){
            Intent newActivity = new Intent(v.getContext(), MetricCalculator.class);
            startActivity(newActivity);
        }
        else if (position == 2){
            Intent newActivity = new Intent(v.getContext(), BasicCalculator.class);
            startActivity(newActivity);
        }
        else if (position == 3){
            Intent newActivity = new Intent(v.getContext(), MaterialCalculator.class);
            startActivity(newActivity);
        }
    }

}
