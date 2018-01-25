package com.example.cspclab.myapplication1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cspclab on 2017-12-19.
 */

public class MenuFragment extends Fragment {
    public MenuFragment() { // Required empty public constructor
    }

    String r_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_menu, container, false);;

        r_name=((orderActivity)getActivity()).menu;
        ImageView imageView = (ImageView)v.findViewById(R.id.menu);
        if(r_name.equals("미분당")){
            imageView.setBackgroundResource(R.drawable.res1_menu);
        }else if(r_name.equals("부탄츄")){
            imageView.setBackgroundResource(R.drawable.res2_menu);
        }else{
            imageView.setBackgroundResource(R.drawable.res3_menu);
        }

        return v;
    }


}


