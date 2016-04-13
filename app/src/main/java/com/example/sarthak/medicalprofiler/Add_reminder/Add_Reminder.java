package com.example.sarthak.medicalprofiler.Add_reminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sarthak.medicalprofiler.DatabaseClasses.ReminderDatabaseObject;
import com.example.sarthak.medicalprofiler.R;

import java.util.Calendar;

/**
 * Created by Sarthak on 1/27/2016.
 */
public class Add_Reminder extends AppCompatActivity {
    private int images[] = {R.drawable.ic_label_black_24dp, R.drawable.ic_query_builder_black_24dp, R.drawable.ic_date_range_black_24dp};
    private String label[] = {"Label", "Pick a time", "Pick a date"};
    private String description[] = {"Enter description", "pick a time", "pick a date"};
    private ListView listView;
    private String enteredTitle;
    private String enteredDescription;
    private static final int TIME_PICKER_DIALOG_ID = 0;
    private static final int DATE_PICKER_DIALOG_ID = 1;
    private PickedTimeDate pickedTimeDate;
    private Add_reminder_adapter adapter_listview;
    LinearLayout week_days_buttons_layout;
    private CheckBox checkBox_repeat_alarm;
    /*Buttons monday-sunday*/
    /*
        0-Sun
        1-Mon
        2-Tue
        3-Wed
        4-Thu
        5-Fri
        6-Sat
     */
    private CheckBox week_days[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);


        pickedTimeDate = new PickedTimeDate();
        /*Floating Action Button*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveReminderFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.note_add_reminder);
                enteredTitle = editText.getText().toString();
                pickedTimeDate.setTitle(enteredTitle);
                ReminderDatabaseObject reminderDatabaseObject=new ReminderDatabaseObject(pickedTimeDate.getTitle(),pickedTimeDate.getDescription(),
                        ""+pickedTimeDate.getDate()+"/"+pickedTimeDate.getMonth()+";"+pickedTimeDate.getHour()+":"+pickedTimeDate.getMinute());
                reminderDatabaseObject.save();
                createAlarmForNotification();
            }
        });


       listView = (ListView) findViewById(R.id.listView_date_time_selector);
        adapter_listview = new Add_reminder_adapter(this, images, description, label);
        listView.setAdapter(adapter_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getDescription();
                        break;
                    case 1:
                        showDialog(TIME_PICKER_DIALOG_ID);
                        break;
                    case 2:
                        showDialog(DATE_PICKER_DIALOG_ID);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /*get Description alert input dialog*/
    private void getDescription() {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Label");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Enter the description");
        builder.setView(input);

        //Set Positive Button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputFromEditTextAlert = input.getText().toString();
                description[0] = inputFromEditTextAlert;
                pickedTimeDate.setDescription(description[0]);
                adapter_listview.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*Time and Date Pickers*/
    protected TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pickedTimeDate.setHour(hourOfDay);
                    pickedTimeDate.setMinute(minute);
                    description[1] = "" + pickedTimeDate.getHour() + ":" + pickedTimeDate.getMinute();
                    adapter_listview.notifyDataSetChanged();
                    Toast.makeText(Add_Reminder.this, "" + description[1], Toast.LENGTH_SHORT).show();
                }
            };

    protected DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pickedTimeDate.setDate(dayOfMonth);
                    pickedTimeDate.setMonth(monthOfYear + 1);
                    pickedTimeDate.setYear(year);
                    description[2] = "" + pickedTimeDate.getDate() + "/" + pickedTimeDate.getMonth() + "/" + pickedTimeDate.getYear();
                    adapter_listview.notifyDataSetChanged();
                    Toast.makeText(Add_Reminder.this, "" + description[2], Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == TIME_PICKER_DIALOG_ID) {
            final Calendar c = Calendar.getInstance();
            return new TimePickerDialog(Add_Reminder.this, timePickerListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
        } else if (id == DATE_PICKER_DIALOG_ID) {
            final Calendar c = Calendar.getInstance();
            Log.d("month", "" + c.get(Calendar.MONTH));
            return new DatePickerDialog(Add_Reminder.this, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    private void createAlarmForNotification() {
        Log.d("et", enteredTitle);
        CreateAlarm createAlarm = new CreateAlarm(this, pickedTimeDate);
        createAlarm.createAlarmToNotify();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("title",pickedTimeDate.getTitle());
        returnIntent.putExtra("description",pickedTimeDate.getDescription());
        returnIntent.putExtra("date", "" + pickedTimeDate.getHour() + "/" + pickedTimeDate.getMinute());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
