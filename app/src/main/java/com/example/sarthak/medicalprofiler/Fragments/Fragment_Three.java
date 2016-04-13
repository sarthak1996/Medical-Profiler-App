package com.example.sarthak.medicalprofiler.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.example.sarthak.medicalprofiler.Config;
import com.example.sarthak.medicalprofiler.ExpandableListView.ExpandableListAdapter_Report_Parameter;
import com.example.sarthak.medicalprofiler.ExpandableListView_Report_Parameter.Report_Parameter_Get;
import com.example.sarthak.medicalprofiler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak on 1/24/2016.
 */
public class Fragment_Three extends Fragment{
    private Button submit_report_parameter;
    ExpandableListAdapter_Report_Parameter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    SearchView searchView_report_parameter;
    FloatingActionButton evaluateParameters;
    private int lastExpandedPosition=-1;
    ArrayList<String> dis;
        public Fragment_Three() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.report_parameter_layout, container,false);

             /*ExpandableListView for report parameters name and response */
            expListView = (ExpandableListView) view.findViewById(R.id.expandable_listView_parameters);
            searchView_report_parameter=(SearchView) view.findViewById(R.id.search_Report_parameter);
            listDataHeader= Report_Parameter_Get.getReportParameters();
            listAdapter = new ExpandableListAdapter_Report_Parameter(getContext(), listDataHeader);
            expListView.setAdapter(listAdapter);
            //search through the list
            dis = new ArrayList<>();
            searchView_report_parameter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    listAdapter.getFilter().filter(newText);
                    return false;
                }
            });
            //Collapse all the other list views
            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                        expListView.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
                }
            });

            evaluateParameters=(FloatingActionButton)view.findViewById(R.id.evaluateParametersFab);
            evaluateParameters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    predictDisease();
                    String display = printDiseases();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                            .setTitle("Diseases you may have")
                            .setMessage(display)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();

                }
            });

            return view;
        }

    private String printDiseases() {
        String _text = "";
        for (int i = 0; i < dis.size(); i++) {
            _text += dis.get(i) + "\n";
        }
        return _text;
    }

    private void predictDisease() {
        String parent_name = "";
        double value_parent = 2;
        dis.clear();
        SharedPreferences preferences = getContext().getSharedPreferences("Med", Context.MODE_PRIVATE);
        preferences.getString(parent_name, "");
        /*
        *
        *
        * public String rbc="rbc";
        * public String wbc="wbc";
        * public String blood_pressure="blood pressure";
        * public String pulse="pulse";
        * public String body_temperature="body temperature";
        * public String hemoglobin="hemoglobin";
        *
        *
        * */

        String tempVars[] = {Config.rbc, Config.wbc, Config.blood_pressure, Config.pulse, Config.body_temperature, Config.hemoglobin};
        String tempValue = "";
        for (int i = 0; i < tempVars.length; i++) {
            tempValue = preferences.getString(tempVars[i], "");
            if (tempValue == null || tempValue.equals("") || tempValue.isEmpty()) {
                value_parent = 0.0;
                continue;
            } else {
                value_parent = Double.valueOf(tempValue);
            }
            if (tempVars[i].equals(Config.rbc))
                get_rbc(value_parent);

            if (tempVars[i].equals(Config.wbc))
                get_wbc(value_parent);

            if (tempVars[i].equals(Config.blood_pressure))
                get_bp(value_parent);

            if (tempVars[i].equals(Config.pulse))
                get_pulse(value_parent);

            if (tempVars[i].equals(Config.body_temperature))
                get_bt(value_parent);

            if (tempVars[i].equals(Config.hemoglobin))
                get_hemo(value_parent);
        }

    }

    private void get_rbc(double value_parent) {

        if (value_parent > 5.72) /*trillion cells per liter*/ {
            dis.add("dehydration");
            dis.add("Sleep apnea");
            dis.add("Malnutrition");
            dis.add("bone marrow failure");
        }

        if (value_parent < 4.32) /*trillion(10^12) cells per liter*/ {
            dis.add("Anemia");
            dis.add("Leukemia");
            dis.add("Malnutrition");
            dis.add("bone marrow failure");
        }

    }

    private void get_wbc(double value_parent) {
        if (value_parent < 4) // 10^9 per liter
        {
            dis.add("Hypersplenism");
            dis.add("Tuberculosis");
            dis.add("Leukemia");
            dis.add("Rheumatoid arthritis");
            dis.add("Lupus");
        }

        if (value_parent > 10) {
            dis.add("Myelofibrosis");
            dis.add("Rheumatoid arthritis");
            dis.add("Tuberculosis");
            dis.add("Myelofibrosis");
            dis.add("Polycythemia vera");
        }
    }

    private void get_bp(double value_parent) {
        if (value_parent < 80)     //Systolic
        {
            dis.add("Diabetes");
            dis.add("Hypotension");
            dis.add("Septic shock");
            dis.add("anaphylaxis");
        }

        if (value_parent > 140)   //Systolic
        {
            dis.add("hypertension");
            dis.add("Sleep apnea");
        }
    }

    private void get_pulse(double value_parent) {
        if (value_parent < 60) {
            dis.add("Bradycardia");
        }

        if (value_parent > 100) {
            dis.add("Tachycardia");
            dis.add("hypertension");
        }
    }

    private void get_bt(double value_parent) {
        if (value_parent < 35) {
            dis.add("Hypothermia");
        }

        if (value_parent > 37.5)
            dis.add("Fever");
    }

    private void get_hemo(double value_parent) {
        if (value_parent < 140) {
            dis.add("anemia");
            dis.add("Cirrhosis");
            dis.add("Hypothyroidism");
            dis.add("Leukemia");
        }

        if (value_parent > 175) {
            dis.add("Emphysema");
            dis.add("Polycythemia vera");
            dis.add("chronic obstructive pulmonary disease");
        }
    }
}
