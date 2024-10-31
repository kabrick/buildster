package com.kaizen.buildster;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kaizen.buildster.database.MyDBHelper;

import java.net.Inet4Address;

import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends ListFragment {

    SharedPreferences prefs = null;


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        String[] values = new String[]{
                "Backup projects", "Delete all projects", "Set password for app", "Report a bug", "Edit prices for materials"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_placeholder, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView i, View v, int position, long id){
        super.onListItemClick(i, v, position, id);
        if (position == 0){
            Toast.makeText(getContext(), "Feature not ready", Toast.LENGTH_LONG).show();
        }
        else if (position == 1){
            MyDBHelper db = new MyDBHelper(getContext());
            db.delete();
            restart();
        }
        else if (position == 2){
            Toast.makeText(getContext(), "This feature is not available in this version", Toast.LENGTH_LONG).show();
        }
        else if (position == 3){
            Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "kabuyedouglas53@gmail.com", null));
            email.putExtra(Intent.EXTRA_SUBJECT, "Buildster Bug Report");
            startActivity(Intent.createChooser(email, "Send bug report..."));
        }
        else if (position == 4){
            Intent intent = new Intent(getContext(), cost.class);
            startActivity(intent);
        }

    }

    private void restart(){
        Intent mStartActivity = new Intent(getContext(), MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

}
