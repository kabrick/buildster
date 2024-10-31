package com.kaizen.buildster;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.kaizen.buildster.database.MyDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProjectsChildFragment extends Fragment {

    ListView listView;
    Cursor cursor;
    TextView textView;
    String projectName;
    View view;


    public ProjectsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_child_project1, container, false);
        listView = (ListView)view.findViewById(R.id.list3);

        MyDBHelper dbHelper = new MyDBHelper(getContext());
        cursor = dbHelper.getProjectList();

        String[] from = new String[]{
                MyDBHelper.PROJECT_LIST_ID, MyDBHelper.PROJECT_LIST_NAME
        };
        int[] to = new int[]{ R.id.id, R.id.projectname};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.listview_template, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView = (TextView)view.findViewById(R.id.projectname);
                Intent intent = new Intent(getContext(), ProjectDisplay.class);
                intent.putExtra("name", textView.getText().toString());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                textView = (TextView)view.findViewById(R.id.projectname);
                projectName = textView.getText().toString();

                    PopupMenu popup = new PopupMenu(getContext(), view);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.contextmenu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
                    popup.show();

                return true;
            }
        });

        return view;
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_delete:
                    delete();
                    return true;
                case R.id.menu_edit:
                    edit();
                    return true;
                default:
            }
            return false;
        }
    }

    public void delete(){
        MyDBHelper db = new MyDBHelper(getContext());
        db.deleteProject(projectName);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.attach(this);
        ft.commit();
    }

    public void edit(){
        Snackbar.make(view, "Feature Not Ready", Snackbar.LENGTH_LONG).setAction("Okay", null).show();
    }

}


