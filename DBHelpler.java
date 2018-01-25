package com.example.cspclab.myapplication1;

/**
 * Created by cspclab on 2017-12-19.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String Ptable = "Pt";
    public static final String Rtable = "Rtab";
    public static final String review_T = "rev";

    //Users : id, pw, test : id, pw, card, usertype

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("tag","db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    }

    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE " + Ptable + "(id text, pw text, card text, usertype text r_name text)";
        String sql_r = "CREATE TABLE " + Rtable + "(name text, info text, menu text, review text, people integer)";
        String sql_review = "CREATE TABLE " + review_T + "(context text, name text)";

        db.execSQL(sql);
        db.execSQL(sql_r);
        db.execSQL(sql_review);

    }

    public void insertPtable(SQLiteDatabase db, String id, String pw, String card, String usertype, String r_name){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + Ptable + "(id, pw, card, usertype)" + "values('"+ id +"', '"+pw+"', '"+card+"', '"+usertype+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }


    public void insertRtable(SQLiteDatabase db, String name, String info, String menu, String review, int people){
        Log.i("tag","대기표 발급시 실행");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + Rtable + "(name,info,menu,review,people)" + "values('"+ name +"', '"+info+"', '"+menu+"', '"+review+"','"+people+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void insert_revtable(SQLiteDatabase db, String context,String name){
        Log.i("tag","회원가입을 했을때 실행함");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " + review_T + "(context,name)" + "values('"+context+"','"+name+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void UpdateRtable(SQLiteDatabase db, String name, int people){
        Log.i("tag","대기표 발급시 실행");
        db.beginTransaction();
        try {
            String sql = "UPDATE " + Rtable + " SET people='" + people + "' WHERE name='"+ name+"'";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}
