package com.kaizen.buildster;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends Fragment {

    private String[] ListArray = { "Version 1.0", "Official Website", "More Apps", "Feedback", "Rate App"};

    ListView listView;
    ArrayAdapter arrayAdapter;



    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        listView = (ListView) view.findViewById(R.id.lists);

        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, ListArray);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kabricks.com"));
                    startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kabricks.com"));
                    startActivity(intent);
                }
                else if (position == 3){
                    Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "kabuyedouglas53@gmail.com", null));
                    email.putExtra(Intent.EXTRA_SUBJECT, "Buildster Feedback");
                    startActivity(Intent.createChooser(email, "Send Feedback..."));
                }
                else if (position == 4){
                    Intent intent = new Intent(getContext(), RateApp.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

}
