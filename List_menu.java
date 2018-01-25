package com.example.cspclab.myapplication1;

import android.graphics.drawable.Drawable;

/**
 * Created by R912 on 2017-12-22.
 */

public class List_menu  {

        private Drawable iconDrawable;
        private String titleStr;
        private String descStr;
        private int descStr2;

        public void setIcon(Drawable icon) {
            iconDrawable = icon;
        }

        public void setTitle(String title) {
            titleStr = title;
        }

        public void setDesc(String desc,int desc2) {
            descStr = desc;
            descStr2 = desc2;
        }

        public void setDesc2(int desc2) {
        descStr2 = desc2;
        }

        public Drawable getIcon() {
            return this.iconDrawable;
        }

        public String getTitle() {
            return this.titleStr;
        }

        public String getDesc() {
            return this.descStr;
        }

    public String getDesc2() {
        return (" X " + Integer.toString(this.descStr2));
    }
}
