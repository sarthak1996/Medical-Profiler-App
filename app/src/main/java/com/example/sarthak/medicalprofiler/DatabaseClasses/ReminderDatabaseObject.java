package com.example.sarthak.medicalprofiler.DatabaseClasses;

import com.orm.SugarRecord;

/**
 * Created by Sarthak on 2/4/2016.
 */
public class ReminderDatabaseObject extends SugarRecord {
    private String title;
    private String description;
    private String date;


    public ReminderDatabaseObject(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public ReminderDatabaseObject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
