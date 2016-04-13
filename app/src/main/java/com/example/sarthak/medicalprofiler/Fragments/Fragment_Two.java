package com.example.sarthak.medicalprofiler.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.sarthak.medicalprofiler.Add_reminder.Add_Reminder;
import com.example.sarthak.medicalprofiler.DatabaseClasses.ReminderDatabaseObject;
import com.example.sarthak.medicalprofiler.R;

import java.util.ArrayList;

import com.example.sarthak.medicalprofiler.Reminder_all.RecycleViewAdapter;
import com.example.sarthak.medicalprofiler.Reminder_all.SimpleDividerItemDecoration;
import com.google.common.collect.Lists;

/**
 * Created by Sarthak on 1/27/2016.
 */
public class Fragment_Two extends Fragment {
    private ArrayList<ReminderDatabaseObject> reminderDatabaseObject;
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    public Fragment_Two() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.days_reminder, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add_reminder_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start activity for result or just start activity??
                Intent intent = new Intent(getContext(), Add_Reminder.class);
                startActivityForResult(intent, 1);
            }
        });
        new ReminderDatabaseObject();
        reminderDatabaseObject = Lists.newArrayList(ReminderDatabaseObject.findAll(ReminderDatabaseObject.class));
        adapter = new RecycleViewAdapter(getContext(), reminderDatabaseObject);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_all_reminders);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //implement onclick listener for contextmenu

        Log.d("h", "" + item.getGroupId());
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {
                //Update List
                Bundle bundle = data.getExtras();
                reminderDatabaseObject.add(new ReminderDatabaseObject(bundle.getString("title"), bundle.getString("description"), bundle.getString("date")));
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }
}

