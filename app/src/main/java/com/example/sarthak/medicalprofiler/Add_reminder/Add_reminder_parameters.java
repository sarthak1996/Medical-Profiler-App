package com.example.sarthak.medicalprofiler.Add_reminder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sarthak on 1/30/2016.
 */
public class Add_reminder_parameters {
    private TextView description;
    private ImageView image;
    private TextView label;
    public Add_reminder_parameters() {
    }


    public Add_reminder_parameters(TextView description, ImageView image, TextView label) {

        this.description = description;
        this.image = image;
        this.label = label;
    }

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }
    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
