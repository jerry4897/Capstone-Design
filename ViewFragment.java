package com.example.cspclab.myapplication1;

import android.app.Fragment;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by cspclab on 2017-12-19.
 */

public class ViewFragment extends Fragment {
    ArrayList<String> items;
    ArrayAdapter<String> listViewAdapter;

    ListView listView;

    EditText editText;
    Button button;
    Button button2;

    DBHelper helper;
    SQLiteDatabase database;

    String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_view, container, false);

        items = new ArrayList<String>();

        helper = new DBHelper(getActivity(), DBHelper.review_T, null, 1);
        database = helper.getWritableDatabase();
        name = ((orderActivity)getActivity()).name;
        Cursor cursor = database.rawQuery("SELECT context FROM "+ helper.review_T+ " WHERE name = '" + name + "'", null);
        while (cursor.moveToNext()) {
            items.add(cursor.getString(0));
        }

        listView = (ListView) layout.findViewById(R.id.review);

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter(listViewAdapter);


        editText = (EditText)layout.findViewById(R.id.edit_review);
        button = (Button)layout.findViewById(R.id.add);
        //button2 = (Button)layout.findViewById(R.id.delete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();        // EditText에 입력된 문자열값을 얻기


                if (!text.isEmpty()) {                        // 입력된 text 문자열이 비어있지 않으면
                    helper.insert_revtable(database,text,name);

                    items.add(text);                          // items 리스트에 입력된 문자열 추가
                    editText.setText("");                           // EditText 입력란 초기화
                    listViewAdapter.notifyDataSetChanged();           // 리스트 목록 갱신
                }
            }
        });
        return layout;
    }
}
