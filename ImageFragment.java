package com.example.cspclab.myapplication1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by R912 on 2017-12-21.
 */

public class ImageFragment  extends Fragment {
    public ImageFragment() { // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageView = (ImageView)v.findViewById(R.id.image);
        if(((orderActivity)getActivity()).image.equals("미분당")) {
            imageView.setBackgroundResource(R.drawable.res1_in);
        }
        else if(((orderActivity)getActivity()).image.equals("부탄츄")){
            imageView.setBackgroundResource(R.drawable.res2_in);
        }
        else{
            imageView.setBackgroundResource(R.drawable.res3_in);
        }

        return v;
    }
}
