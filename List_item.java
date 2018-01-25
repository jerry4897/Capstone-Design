package com.example.cspclab.myapplication1;

import android.graphics.drawable.Drawable;

/**
 * Created by R912 on 2017-12-22.
 */

public class List_item {

    private String titleStr ;
    private String descStr1 ;
    private String descStr2;
    private String descStr3;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc1, String desc2, String desc3) {
        descStr1 = desc1 ;
        descStr2 = desc2;
        descStr3 = desc3;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc1() {
        return this.descStr1 ;
    }
    public String getDesc2() {
        return this.descStr2 ;
    }
    public String getDesc3() {
        return this.descStr3 ;
    }
}
