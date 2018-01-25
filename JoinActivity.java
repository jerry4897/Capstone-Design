package com.example.cspclab.myapplication1;

/**
 * Created by cspclab on 2017-12-19.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Owner on 2017-12-19.
 */

public class JoinActivity extends Activity {
    int version = 1;
    DBHelper helper;
    DBHelper helper2;
    SQLiteDatabase database;
    SQLiteDatabase database2;
    EditText idEditText;
    EditText pwEditText;
    EditText cardEditText;
    EditText userType;
    Button btnJoin;

    String sql;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        idEditText = (EditText) findViewById(R.id.idEditText);
        pwEditText = (EditText) findViewById(R.id.pwEditText);
        cardEditText = (EditText) findViewById(R.id.cardEditText);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);


        btnJoin = (Button) findViewById(R.id.btnJoin);

        helper = new DBHelper(JoinActivity.this, DBHelper.Ptable, null, version);
        helper2 = new DBHelper(JoinActivity.this, DBHelper.Rtable, null, version);
        database = helper.getWritableDatabase();

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();
                String card = cardEditText.getText().toString();
                int rbid = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(rbid);
                String usertype = rb.getText().toString();

                if(id.length() == 0 || pw.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(JoinActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM "+ helper.Ptable + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    //존재하는 아이디입니다.
                    Toast toast = Toast.makeText(JoinActivity.this, "존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String r_name="00";
                    helper.insertPtable(database,id,pw,card,usertype,r_name);

                    database2 = helper2.getWritableDatabase();

                    helper2.insertRtable(database2,"미분당","오픈: 11시" + "\n" + "마감: 20시" + "\n" + "전화번호: 02)3141-0807","메뉴는 쌀국수","10",5);
                    helper2.insertRtable(database2,"부탄츄","오픈: 11시" + "\n" + "마감: 21시" + "\n" + "전화번호: 02)3144-6604","메뉴는 ㅠ","10",10);
                    helper2.insertRtable(database2,"겐로쿠우동","오픈: 11시" + "\n" + "마감: 20시" + "\n" + "전화번호: 02)365-8555","메뉴는 밥","15",10);
                    database.close();
                    Toast toast = Toast.makeText(JoinActivity.this, "가입이 완료되었습니다. 로그인을 해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
