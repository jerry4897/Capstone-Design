package com.example.cspclab.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by cspclab on 2017-12-19.
 */

public class FavoriteActivity extends AppCompatActivity {

    public String name;
    static public int i=0;
    static public ArrayList<String> LIST= new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LIST) ;

        ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                // get TextView's Text.
                String strText = (String) parent.getItemAtPosition(position) ;
                Intent intent = new Intent(FavoriteActivity.this, orderActivity.class);
                intent.putExtra("Name","미분당");
                startActivity(intent);
                // TODO : use strText
            }
        }) ;
    }
}
