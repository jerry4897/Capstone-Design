package com.example.cspclab.myapplication1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by cspclab on 2017-12-19.
 */

public class orderActivity extends AppCompatActivity {

    private int isFragment = 0 ;
    String sql;
    Cursor cursor;
    DBHelper helper;
    SQLiteDatabase database;
    public String name;
    String info;
    String menu;
    String image;
    int people=0;

    FavoriteActivity FA;

    public static Context oContext;
/*
    private static final int PORT = 10001; //서버에서 설정한 PORT 번호
    String ip="10.1.151.144"; //서버 단말기의 IP주소..
    Socket socket;     //클라이언트의 소켓
    DataInputStream is;
    DataOutputStream os;

    EditText edit_ip;   //서버의 IP를 작성할 수 있는 EditText
    String msg="";
    boolean isConnected=true;

*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
       // edit_ip.setText(ip);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentBorC, new InfoFragment());
        fragmentTransaction.commit();



        oContext=this;

        Intent intents = getIntent();
        name=intents.getStringExtra("Name");
        image=name;
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fm1.beginTransaction();
        fragmentTransaction1.replace(R.id.fragmentA, new ImageFragment());
        fragmentTransaction1.commit();
        Button launch1 = (Button) findViewById(R.id.button1);
        Button launch2 = (Button) findViewById(R.id.button2);
        Button launch3 = (Button) findViewById(R.id.button4);


        Button frag1 = (Button)findViewById(R.id.button3);
        Button frag2 = (Button)findViewById(R.id.button5);
        Button frag3 = (Button)findViewById(R.id.button6);

        helper = new DBHelper(orderActivity.this, DBHelper.Rtable, null, 1);
        database = helper.getWritableDatabase();
        sql = "SELECT info FROM "+ helper.Rtable + " WHERE name = '" + name + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        info = "식당이름 : " + name + "\n" + cursor.getString(0);

        sql = "SELECT menu FROM "+ helper.Rtable + " WHERE name = '" + name + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        menu = name;





        frag1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFragment = 0;
                switchFragment() ;
            }
        });
        frag2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFragment = 1;
                switchFragment() ;
            }
        });
        frag3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFragment = 2;
                switchFragment() ;
            }
        });
   /*     new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    ip= edit_ip.getText().toString();//IP 주소가 작성되어 있는 EditText에서 서버 IP 얻어오기
                    //서버와 연결하는 소켓 생성..
                    socket= new Socket(InetAddress.getByName(ip), PORT );
                    //여기까지 왔다는 것을 예외가 발생하지 않았다는 것이므로 소켓 연결 성공..
                    //서버와 메세지를 주고받을 통로 구축
                    is=new DataInputStream(socket.getInputStream());
                    os=new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }//run method...
        }).start();//Thread 실행..*/
        launch1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                helper = new DBHelper(orderActivity.this, DBHelper.Rtable, null, 1);
                database = helper.getWritableDatabase();
                sql = "SELECT people FROM "+ helper.Rtable + " WHERE name = '" + name + "'";
                cursor = database.rawQuery(sql, null);
                cursor.moveToNext();
                people = cursor.getInt(0)+1;

                //Toast.makeText(getApplicationContext(),Integer.toString(people),Toast.LENGTH_SHORT).show();
                Intent j;



                helper.UpdateRtable(database,name,people);

                sql = "SELECT review FROM "+ helper.Rtable + " WHERE name = '" + name + "'";
                cursor = database.rawQuery(sql, null);
                cursor.moveToNext();

                ((MainActivity2)MainActivity2.mContext).update_waiting(name,people,cursor.getString(0));

                j = new Intent(orderActivity.this,date.class);

                startActivity(j);

                finish();
            }

        });
        launch2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"쿠폰",Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        launch3.setOnClickListener(new ToggleButton.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"LIKE",Toast.LENGTH_SHORT).show();
                if(!FA.LIST.contains(name)){
                    FA.LIST.add(name);
                }
                else{
                    FA.LIST.remove(name);
                }
               // (( FavoriteActivity)FavoriteActivity.fContext).add_list(name);
            }
        });

    }

    public void switchFragment() {
        Fragment fr;
        if (isFragment==0) {
            fr = new InfoFragment();


        } else if(isFragment==1) {
            fr = new MenuFragment();


        } else {
            fr = new ViewFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fr);
        fragmentTransaction.commit();
    }
}
