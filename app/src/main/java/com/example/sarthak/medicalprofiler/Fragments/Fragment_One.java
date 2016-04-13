package com.example.sarthak.medicalprofiler.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sarthak.medicalprofiler.R;

/**
 * Created by Sarthak on 1/23/2016.
 */
public class Fragment_One extends Fragment {
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
        View view= inflater.inflate(R.layout.fragment_class, container, false);
        CardView brain=(CardView) view.findViewById(R.id.card_view_brain);
        brain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open a dialog to prompt the user to choose color
            }
        });

        return view;
    }
}
