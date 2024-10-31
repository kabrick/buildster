package com.kaizen.buildster;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaizen.buildster.database.MyDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectsFragment extends Fragment {

    Button button;
    EditText editText;
    String userInput;


    public ProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_projects, container, false);
        button = (Button)view.findViewById(R.id.newProject);

        getList();

        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                LayoutInflater inflater1 = LayoutInflater.from(getContext());
                View view1 = inflater1.inflate(R.layout.alert_dialog, null);

                editText = (EditText)view1.findViewById(R.id.project_name);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("New Project Dialog");
                alertDialog.setView(view1);

                alertDialog.setCancelable(false)
                        .setPositiveButton("Okay",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        userInput = editText.getText().toString();
                                        valueCreate(userInput);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog1 = alertDialog.create();

                alertDialog1.show();
            }
        });


        return view;
    }

    public void getList(){
        MyDBHelper dbHelper = new MyDBHelper(getContext());
        boolean result = dbHelper.projectList();

        if (result){
            //if table is not empty
            Fragment frag1 = new ProjectsChildFragment();
            enableFragment(frag1);
        }
        else {
            //table is empty
            Fragment frag2 = new ProjectsChildsFragment();
            enableFragment(frag2);
        }
    }

    public void valueCreate(String projectName){
        MyDBHelper dbHelper = new MyDBHelper(getContext());
        String result;
        result = dbHelper.addProject(projectName);
        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), ProjectDisplay.class);
        intent.putExtra("name", projectName);
        recreateFragment();
        startActivity(intent);
    }

    private void enableFragment(Fragment fragment){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //use replace instead of add to avoid unpredictable behaviour
        transaction.replace(R.id.child_fragment, fragment).commit();
    }

    public void recreateFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this);
        ft.attach(this);
        ft.commit();
    }
}
