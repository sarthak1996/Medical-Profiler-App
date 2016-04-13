package com.example.sarthak.medicalprofiler.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SearchView;

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
                    Snackbar.make(view, "Evaluate Parameters", Snackbar.LENGTH_LONG)
                            .setAction("Done", new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    //on click fab;
                                }
                            }).show();
                }
            });

            return view;
        }
}
