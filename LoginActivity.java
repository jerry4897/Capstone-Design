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
import android.widget.Toast;

public class LoginActivity extends Activity {

    int version = 1;
    DBHelper helper;
    SQLiteDatabase database;

    EditText idEditText;
    EditText pwEditText;
    Button btnLogin;
    Button btnJoin;

    String sql;
    Cursor cursor;
    String who = "고객";

    public String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = (EditText) findViewById(R.id.idEditText);
        pwEditText = (EditText) findViewById(R.id.pwEditText);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        helper = new DBHelper(LoginActivity.this, DBHelper.Ptable, null, version);
        database = helper.getWritableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();

                if(id.length() == 0 || pw.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM "+ helper.Ptable + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                if(cursor.getCount() != 1){
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT pw FROM "+ helper.Ptable + " WHERE id = '" + id + "'";
                cursor = database.rawQuery(sql, null);

                cursor.moveToNext();
                if(!pw.equals(cursor.getString(0))){
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //로그인성공
                    Toast toast = Toast.makeText(LoginActivity.this, "로그인성공", Toast.LENGTH_SHORT);
                    toast.show();
                    //인텐트 생성 및 호출
                    sql = "SELECT usertype FROM "+ helper.Ptable + " WHERE id = '" + id + "'";
                    cursor = database.rawQuery(sql, null);

                    cursor.moveToNext();


                    //인텐트 생성 및 호출
                    if(!who.equals(cursor.getString(0))){
                        cursor.close();
                        Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                        startActivity(intent);
                    }
                    else{
                        cursor.close();
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        intent.putExtra("Id",id);
                        startActivity(intent);
                    }
                }

            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Toast toast = Toast.makeText(LoginActivity.this, "회원가입 화면으로 이동", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
                //finish();
            }
        });

    }
}
