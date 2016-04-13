package com.example.sarthak.medicalprofiler.Reminder_all;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.sarthak.medicalprofiler.DatabaseClasses.ReminderDatabaseObject;
import com.example.sarthak.medicalprofiler.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sarthak on 2/10/2016.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CustomViewHolder> {
    private static Context ctx;
    private ArrayList<ReminderDatabaseObject> reminders;
    private LayoutInflater inflater;
    public static ContextMenu.ContextMenuInfo info;
    public RecycleViewAdapter(Context ctx, ArrayList<ReminderDatabaseObject> reminders) {
        this.ctx = ctx;
        inflater=LayoutInflater.from(this.ctx);
        this.reminders = reminders;
    }

    public RecycleViewAdapter() {

    }

    @Override
    public RecycleViewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.days_reminder_all_adapter,parent,false);
        CustomViewHolder customViewHolder=new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.CustomViewHolder holder, final int position) {
        holder.setTitle(reminders.get(position).getTitle());
        holder.setDescription(reminders.get(position).getDescription());
        holder.setDate(reminders.get(position).getDate());
        String title=reminders.get(position).getTitle();
        if(title==null || title.length()==0)
            title="X";
        holder.setDrawable(TextDrawable.builder()
                .buildRound("" + title.toUpperCase().charAt(0), getRandomColor()));
        holder.setImage();

    }

    public int getRandomColor(){
        int color[]={
                Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,Color.GREEN,
                Color.MAGENTA,Color.RED,Color.YELLOW };
        Random random=new Random();
        return color[random.nextInt(color.length-1)];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return reminders.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView title;
        private TextView description;
        private TextView date;
        private TextDrawable drawable;
        private ImageView image;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            title=(TextView)itemView.findViewById(R.id.title);
            description=(TextView) itemView.findViewById(R.id.description);
            date=(TextView)itemView.findViewById(R.id.date);
            image = (ImageView) itemView.findViewById(R.id.image_view);
            itemView.setClickable(true);
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage() {
            this.image.setImageDrawable(drawable);
        }


        public TextDrawable getDrawable() {
            return drawable;
        }

        public void setDrawable(TextDrawable drawable) {
            this.drawable = drawable;
        }

        public String getTitle() {
            return title.getText().toString();
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }
        public String getDescription() {
            return description.getText().toString();
        }

        public void setDescription(String description) {
            this.description.setText(description);
        }
        public String getDate() {
            return date.getText().toString();
        }

        public void setDate(String date) {
            this.date.setText(date);
        }

    }
}
