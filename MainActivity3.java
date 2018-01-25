package com.example.cspclab.myapplication1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity3  extends AppCompatActivity{


    ListView listview ;
    ListAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // Adapter 생성
        adapter = new ListAdaptor() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview2);
        listview.setAdapter(adapter);



        // 첫 번째 아이템 추가.
        adapter.addItem("김석환", "17:30","01044554455","3 명") ;
        // 두 번째 아이템 추가.
        adapter.addItem("장인종", "18:00","01051559487","2 명") ;
        // 세 번째 아이템 추가.
        Button res1 = (Button) findViewById(R.id.list_add);
        res1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.add_edit1);
                EditText editText2 = (EditText) findViewById(R.id.add_edit2);
                EditText editText3 = (EditText) findViewById(R.id.add_edit3);
                EditText editText4 = (EditText) findViewById(R.id.add_edit4);

                adapter.addItem(editText1.getText().toString(), editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString()+" 명") ;
                adapter.notifyDataSetChanged();
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                List_item item = (List_item) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr1 = item.getDesc1() ;
                String descStr2 = item.getDesc2() ;
                String descStr3 = item.getDesc3() ;
                String menu;

                if(titleStr.equals("김석환")){
                    menu="양지쌀국수 X 2 <br> 차돌박이쌀국수 X 1";
                }
                else if(titleStr.equals("장인종")){
                    menu="해산물쌀국수 X 2";
                }
                else{
                    menu="양지쌀국수 X 2";
                }
                DialogHtmlView(menu);

            }
        }) ;


    }

    private void DialogHtmlView(String menu) {
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity3.this);
        ab.setMessage(Html.fromHtml("<strong><font color=\"#ff0000\"> " + "주문 메뉴"
                + "</font></strong><br>"+menu));
        ab.setPositiveButton("ok", null);
        ab.show();
    }


}
