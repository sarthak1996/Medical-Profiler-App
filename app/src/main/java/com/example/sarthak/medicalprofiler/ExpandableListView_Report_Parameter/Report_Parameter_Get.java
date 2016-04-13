package com.example.sarthak.medicalprofiler.ExpandableListView_Report_Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sarthak on 1/25/2016.
 */
public class Report_Parameter_Get {
    public static List<String> getReportParameters(){
        List<String> report_parameters=new ArrayList<>();
        report_parameters.add("blood group");
        report_parameters.add("rbc");
        report_parameters.add("wbc");
        return report_parameters;
    }
}
