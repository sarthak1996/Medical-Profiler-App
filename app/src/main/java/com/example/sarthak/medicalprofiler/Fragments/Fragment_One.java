package com.example.sarthak.medicalprofiler.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarthak.medicalprofiler.R;

/**
 * Created by Sarthak on 1/23/2016.
 */
public class Fragment_One extends Fragment {
    private String color;
    String colorWhich[];
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;


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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.evaluateMood);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoodAlertDialog();
            }
        });

        CardView color1=(CardView)view.findViewById(R.id.card_view_1);
        CardView color2=(CardView)view.findViewById(R.id.card_view_2);
        CardView color3=(CardView)view.findViewById(R.id.card_view_3);
        CardView color4=(CardView)view.findViewById(R.id.card_view_4);
        CardView color5=(CardView)view.findViewById(R.id.card_view_5);
        CardView color6=(CardView)view.findViewById(R.id.card_view_6);
        colorWhich = new String[6];
        textView1 = (TextView) view.findViewById(R.id.image_view1);
        textView2 = (TextView) view.findViewById(R.id.image_view2);
        textView3 = (TextView) view.findViewById(R.id.image_view3);
        textView4 = (TextView) view.findViewById(R.id.image_view4);
        textView5 = (TextView) view.findViewById(R.id.image_view5);
        textView6 = (TextView) view.findViewById(R.id.image_view6);
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView1.setText("Color for 1:" + color);
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView2.setText("Color for 2:" + color);
            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView3.setText("Color for 3:" + color);
            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView4.setText("Color for 4:" + color);
            }
        });
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView5.setText("Color for 5:" + color);
            }
        });
        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColorDialog();
                textView6.setText("Color for 6:" + color);
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
                        color[0] = which;
                    }
                });
        builder.create();
        builder.show();
        this.color = colors[color[0]];
        return color[0];
    }

    private void createMoodAlertDialog() {
        String message[] = {"You seem to be happy today\nGood for you\nBeing happy keeps you healthy",
                "Why are you sad! Talk to someone...Maybe that would help\n",
                "Whoa!! Being angry is not cool.. Calm down user...Keep Calm\nMy masters are very calm guyz..You would love them"};
        String mood = detectMood();
        if (mood == null || mood == "" || mood.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter all the parameters", Toast.LENGTH_SHORT).show();
            return;
        }
        int i = 0;
        if (mood.equals("Happy"))
            i = 0;
        if (mood.equals("Sad"))
            i = 1;
        if (mood.equals("Angry"))
            i = 2;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Your mood")
                .setMessage(message[i]);
        builder.show();
    }

    private String detectMood() {
         final String colors[]={"Red","Blue","Brown","Yellow","Green"};
        int ret = getColorWhich();
        if (ret == 0)
            return null;
         /*
         *
         * colorWhich[0]-label1
         *
         * */
         if (colorWhich[0].equals("Red") && colorWhich[1].equals("Brown") && colorWhich[2].equals("Brown") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Brown")) {
             //SAD
             return "Sad";
         } else if (colorWhich[0].equals("Blue") && colorWhich[1].equals("Blue") && colorWhich[2].equals("Blue") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //HAPPY
             return "Happy";
         } else if (colorWhich[0].equals("Red") && colorWhich[1].equals("Red") && colorWhich[2].equals("Yellow") && colorWhich[3].equals("Yellow") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //HAPPY
             return "Happy";
         } else if (colorWhich[0].equals("Blue") && colorWhich[1].equals("Blue") && colorWhich[2].equals("Brown") && colorWhich[3].equals("Red") && colorWhich[4].equals("Blue") && colorWhich[5].equals("Blue")) {
             //ANGRY
             return "Angry";
         } else {
             //HAPPY
             return "Happy";
         }
     }

    private int getColorWhich() {
        String sample = textView1.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[0] = sample.trim().split(":")[1];

        sample = textView2.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[1] = sample.trim().split(":")[1];

        sample = textView3.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[2] = sample.trim().split(":")[1];

        sample = textView4.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[3] = sample.trim().split(":")[1];

        sample = textView5.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[4] = sample.trim().split(":")[1];

        sample = textView6.getText().toString();
        if (sample.contains(":")) {
            return 0;
        }
        colorWhich[5] = sample.trim().split(":")[1];
        return 1;
    }
}
