package com.example.sarthak.medicalprofiler.ExpandableListView_Report_Parameter;

import com.example.sarthak.medicalprofiler.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak on 1/25/2016.
 */
public class Report_Parameter_Get {
    public static List<String> getReportParameters(){
        List<String> report_parameters=new ArrayList<>();
        report_parameters.add(Config.rbc);
        report_parameters.add(Config.wbc);
        report_parameters.add(Config.blood_pressure);
        report_parameters.add(Config.pulse);
        report_parameters.add(Config.body_temperature);
        report_parameters.add(Config.hemoglobin);
        return report_parameters;
    }
}
