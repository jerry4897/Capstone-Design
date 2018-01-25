package com.example.cspclab.myapplication1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static com.google.android.gms.internal.zzben.NULL;

public class MainActivity2 extends AppCompatActivity {

    String sql;
    Cursor cursor;
    DBHelper helper;
    SQLiteDatabase database;
    String name1 = "미분당";
    String name2 = "부탄츄";
    String name3 = "겐로쿠우동";

    String Id;
    String l_people1;
    String l_people2;
    String l_people3;
    int people1;
    int people2;
    int people3;





    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intents = getIntent();
        Id=intents.getStringExtra("Id");

        mContext=this;


        LinearLayout res1 = (LinearLayout) findViewById(R.id.imageView4);
        LinearLayout res2 = (LinearLayout) findViewById(R.id.imageView5);
        LinearLayout res3 = (LinearLayout) findViewById(R.id.imageView7);
        TextView res_text1 = (TextView)findViewById(R.id.text4);
        TextView res_text2 = (TextView)findViewById(R.id.text5);
        TextView res_text3 = (TextView)findViewById(R.id.text7);

        helper = new DBHelper(MainActivity2.this, DBHelper.Rtable, null, 1);
        database = helper.getWritableDatabase();

        sql = "SELECT people FROM "+ helper.Rtable + " WHERE name = '" + name1 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        people1=cursor.getInt(0);
        sql = "SELECT review FROM "+ helper.Rtable + " WHERE name = '" + name1 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        l_people1 = cursor.getString(0);
        if(people1>Integer.parseInt(l_people1)){
            people1=people1-Integer.parseInt(l_people1);
        }
        else{
            people1=0;
        }



        sql = "SELECT people FROM "+ helper.Rtable + " WHERE name = '" + name2 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        people2=cursor.getInt(0);
        sql = "SELECT review FROM "+ helper.Rtable + " WHERE name = '" + name2 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        l_people2 = cursor.getString(0);
        if(people2>Integer.parseInt(l_people2)){
            people2=people2-Integer.parseInt(l_people2);
        }
        else{
            people2=0;
        }

        sql = "SELECT people FROM "+ helper.Rtable + " WHERE name = '" + name3 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        people3=cursor.getInt(0);
        sql = "SELECT review FROM "+ helper.Rtable + " WHERE name = '" + name3 + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();
        l_people3 = cursor.getString(0);
        if(people3>Integer.parseInt(l_people3)){
            people3=people3-Integer.parseInt(l_people3);
        }
        else{
            people3=0;
        }

        res_text1.setText("대기 인원 : " + Integer.toString(people1)+"명");

        res_text2.setText("대기 인원 : " + Integer.toString(people2)+"명");

        res_text3.setText("대기 인원 : " + Integer.toString(people3)+"명");

        Button launch2 = (Button) findViewById(R.id.button2);
        Button launch3 = (Button) findViewById(R.id.button1);
        Button launch4 = (Button) findViewById(R.id.button4);
        res1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, orderActivity.class);
                intent.putExtra("Name","미분당");
                startActivity(intent);

            }
        });
        res2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, orderActivity.class);
                intent.putExtra("Name","부탄츄");
                startActivity(intent);

            }
        });
        res3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, orderActivity.class);
                intent.putExtra("Name","겐로쿠우동");
                startActivity(intent);

            }
        });

        launch2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this, NearbyActivity.class);
                startActivity(intent);
            }
        });

        launch3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });



        launch4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });





/*


        new Thread(new Runnable() {



            @Override

            public void run() {
                try {
                    ip= edit_ip.getText().toString();//IP 주소가 작성되어 있는 EditText에서 서버 IP 얻어오기
                    //서버와 연결하는 소켓 생성..
                    socket= new Socket(InetAddress.getByName(ip), PORT );
                    //여기까지 왔다는 것을 예외가 발생하지 않았다는 것이므로 소켓 연결 성공..
                    //서버와 메세지를 주고받을 통로 구축
                    is=new DataInputStream(socket.getInputStream());
                    os=new DataOutputStream(socket.getOutputStream());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //서버와 접속이 끊길 때까지 무한반복하면서 서버의 메세지 수신
                while(true){
                    try {
                        msg= is.readUTF(); //서버 부터 메세지가 전송되면 이를 UTF형식으로 읽어서 String 으로 리턴
                        //서버로부터 읽어들인 메시지msg를 TextView에 출력..
                        //안드로이드는 오직 main Thread 만이 UI를 변경할 수 있기에
                        //네트워크 작업을 하는 이 Thread에서는 TextView의 글씨를 직접 변경할 수 없음.
                        //runOnUiThread()는 별도의 Thread가 main Thread에게 UI 작업을 요청하는 메소드임.
                        runOnUiThread(new Runnable() {



                            @Override

                            public void run() {
                                String arr[] = msg.split("|");
                                if(!d_name.equals(arr[0])){
                                    sql = "SELECT people FROM "+ helper.Rtable + " WHERE name = '" + name1 + "'";
                                    cursor = database.rawQuery(sql, null);
                                    cursor.moveToNext();

                                    helper.UpdateRtable(database,"미분당",cursor.getInt(0)-1);
                                    d_name = arr[0];
                                    update_waiting("00",1,"");
                                }
                            }

                        });
                        //////////////////////////////////////////////////////////////////////////
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }//while
            }//run method...



        }).start();//Thread 실행..

        */
    }





    public void update_waiting(String name, int people,String l_people){

     if(people>Integer.parseInt(l_people)){
            people=people-Integer.parseInt(l_people);
        }
        else{
            people=0;
        }

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(name + Integer.toString(people) + "팀");


        if(name.equals("미분당")) {
            TextView button_view = (TextView) findViewById(R.id.text4);
            button_view.setText("대기 인원 : " + Integer.toString(people)+"명");
        }
        else if(name.equals("부탄츄")){
            TextView button_view = (TextView)findViewById(R.id.text5);
            button_view.setText("대기 인원 : " + Integer.toString(people)+"명");
        }
        else{
            TextView button_view = (TextView) findViewById(R.id.text7);
            button_view.setText("대기 인원 : " + Integer.toString(people)+"명");
        }

    }
    public void update_time(String name, String time){

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(name + "   예약 시간 : " + time);

    }


}
