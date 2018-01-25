package com.example.cspclab.myapplication1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by R912 on 2017-12-22.
 */

public class pay extends AppCompatActivity {
    int[] arr={0,0,0,0,0};
    int price=0;
    ArrayAdapter pay_adapter;
    String[] pay_list= {"0"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat);

        pay_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pay_list) ;
        ListView pay_listview = (ListView) findViewById(R.id.choice) ;

        pay_listview.setAdapter(pay_adapter) ;


        ListView listview ;
        final MenuAdaptor adapter;

        // Adapter 생성
        adapter = new MenuAdaptor() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.menu_list);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menu1),
                "해산물쌀국수", "9000", 0) ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menu2),
                "차돌박이쌀국수", "8000",0) ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menu3),
                "양지쌀국수", "7000",0) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menu4),
                "음료", "2000",0) ;



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                List_menu item = (List_menu) parent.getItemAtPosition(position) ;

                arr[position]=arr[position]+1;
                item.setDesc2(arr[position]);
                adapter.notifyDataSetChanged();

                price = price + Integer.parseInt(item.getDesc());

                pay_list[0]=("결제 금액 : " + Integer.toString(price)+ " 원");
                pay_adapter.notifyDataSetChanged();
                //Toast.makeText(pay.this,Integer.toString(position),Toast.LENGTH_SHORT).show();
            }
        }) ;
        Button launch2 = (Button)findViewById(R.id.pay);
        launch2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"결제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
