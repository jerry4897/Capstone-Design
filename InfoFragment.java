package com.example.cspclab.myapplication1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by cspclab on 2017-12-19.
 */

public class InfoFragment  extends Fragment {
    public InfoFragment() { // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_info, container, false);
        TextView text = (TextView) v.findViewById(R.id.info);
        text.setText(((orderActivity)getActivity()).info);
       // return inflater.inflate(R.layout.fragment_info, container, false);
        return v;
    }
}
