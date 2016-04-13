package com.example.sarthak.medicalprofiler.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sarthak.medicalprofiler.R;

/**
 * Created by Sarthak on 1/23/2016.
 */
public class Fragment_One extends Fragment {
    private int color=0;
    public Fragment_One() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_class, container, false);
        CardView color1=(CardView)view.findViewById(R.id.card_view_1);
        CardView color2=(CardView)view.findViewById(R.id.card_view_2);
        CardView color3=(CardView)view.findViewById(R.id.card_view_3);
        CardView color4=(CardView)view.findViewById(R.id.card_view_4);
        CardView color5=(CardView)view.findViewById(R.id.card_view_5);
        CardView color6=(CardView)view.findViewById(R.id.card_view_6);

        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView=(ImageView) v.findViewById(R.id.image_view2);
                getColorDialog();
                imageView.setBackgroundColor(color);
            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView=(ImageView) v.findViewById(R.id.image_view3);
                getColorDialog();
                imageView.setBackgroundColor(color);
            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView=(ImageView) v.findViewById(R.id.image_view4);
                getColorDialog();
                imageView.setBackgroundColor(color);
            }
        });
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView=(ImageView) v.findViewById(R.id.image_view5);
                getColorDialog();
                imageView.setBackgroundColor(color);
            }
        });
        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView=(ImageView) v.findViewById(R.id.image_view6);
                getColorDialog();
                imageView.setBackgroundColor(color);
            }
        });

        return view;
    }
    private int getColorDialog(){
        final int[] color = new int[1];
        final String colors[]={"Red","Blue","Brown","Yellow","Green"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick a color")
                .setItems(colors, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        color[0] =getColor(colors[which]);
                        ImageView imageView=(ImageView) getView().findViewById(R.id.image_view1);

                        Log.d("color",""+color[0]);
                        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                    }
                });
        builder.create();
        builder.show();
        this.color=color[0];
        return color[0];
    }
    private int getColor(String color){
        int mColor=0;
        if(color.equals("Red")){
            mColor=getResources().getColor(R.color.colorDeepRed);
        }else if(color.equals("Blue")){
            mColor=getResources().getColor(R.color.colorPrimaryDark);
        }else if(color.equals("Brown")){
            mColor=getResources().getColor(R.color.colorBrown);
        }else if(color.equals("Yellow")){
            mColor=getResources().getColor(R.color.colorYellow);
        }else if(color.equals("Green")){
            mColor=getResources().getColor(R.color.colorGreenMap);
        }
        return mColor;
    }
     private void detectMood(){
         final String colors[]={"Red","Blue","Brown","Yellow","Green"};
         int []colorWhich;
         /*
         *
         * colorWhich[0]-label1
         *
         * */
         if (colorWhich[0].equals("Red") && colorWhich[1].equals("Brown") && colorWhich[2].equals("Brown") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Brown")) {
             //SAD
         } else if (colorWhich[0].equals("Blue") && colorWhich[1].equals("Blue") && colorWhich[2].equals("Blue") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //HAPPY
         } else if (colorWhich[0].equals("Red") && colorWhich[1].equals("Red") && colorWhich[2].equals("Yellow") && colorWhich[3].equals("Yellow") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //HAPPY
         } else if (colorWhich[0].equals("Blue") && colorWhich[1].equals("Blue") && colorWhich[2].equals("Brown") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //ANGRY
         } else {
             //HAPPY
         }
     }
}
