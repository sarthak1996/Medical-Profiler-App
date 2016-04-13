package com.example.sarthak.medicalprofiler.ExpandableListView;

/**
 * Created by Sarthak on 1/26/2016.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarthak.medicalprofiler.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter_Report_Parameter extends BaseExpandableListAdapter implements Filterable{

    private Context _context;
    private List<String> _listDataHeader; // header titles
    private List<String> _fixedListDataHeader;
    private CustomFilterExpandableList filterExpandableList;
    private Button submit_report_parameter;

    public ExpandableListAdapter_Report_Parameter(Context context, List<String> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._fixedListDataHeader=_listDataHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.report_parameter_input, null);
        }


        /*Submit button functionality*/
        submit_report_parameter=(Button) convertView.findViewById(R.id.input_parameter_submit_button);
        submit_report_parameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parent_name=getGroup(groupPosition).toString();
                Toast.makeText(_context,parent_name,Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.report_parameter_name, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.report_parameter_name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {
        if(filterExpandableList==null){
            filterExpandableList=new CustomFilterExpandableList();
        }
        return filterExpandableList;
    }


    class CustomFilterExpandableList extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults=new FilterResults();

            if(constraint !=null && constraint.length()>0){
                List<String> filtered_adapter_results=new ArrayList<String>();
                int report_parameter_size=_fixedListDataHeader.size();
                for(int i=0;i<report_parameter_size;i++){
                    if(_fixedListDataHeader.get(i).contains(constraint)){
                        filtered_adapter_results.add(_fixedListDataHeader.get(i));
                    }
                }
                filterResults.count=filtered_adapter_results.size();
                filterResults.values=filtered_adapter_results;
            }else{
                filterResults.count=_fixedListDataHeader.size();
                filterResults.values=_fixedListDataHeader;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            _listDataHeader=(ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
