package com.example.sarthak.medicalprofiler.Add_reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarthak.medicalprofiler.R;

/**
 * Created by Sarthak on 1/30/2016.
 */
public class Add_reminder_adapter extends BaseAdapter {
    private Context ctx;
    private int images[];
    private String description[];
    private String label[];
    private static LayoutInflater inflater=null;

    public Add_reminder_adapter(Context ctx, int[] images, String[] description, String[] label) {
        this.ctx = ctx;
        this.images = images;
        this.description = description;
        this.label = label;
        inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return label.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Add_reminder_parameters row_add_reminder=new Add_reminder_parameters();
        final View row_view=inflater.inflate(R.layout.list_item_pick_date_time,null);
        //Initializing views
        row_add_reminder.setDescription((TextView) row_view.findViewById(R.id.pick_label_description_add_reminder));
        row_add_reminder.setLabel((TextView) row_view.findViewById(R.id.pick_label_add_reminder));
        row_add_reminder.setImage((ImageView) row_view.findViewById(R.id.pick_image_add_reminder));
        //Adding apt details to respective views
        row_add_reminder.getLabel().setText(label[position]);
        row_add_reminder.getDescription().setText(description[position]);
        row_add_reminder.getImage().setImageResource(images[position]);

        return row_view;
    }
}
