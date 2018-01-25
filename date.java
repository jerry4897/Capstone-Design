package com.example.cspclab.myapplication1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by R912 on 2017-12-21.
 */

public class date extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        //CalendarView 인스턴스 만들기
        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
        //리스너 등록
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Intent i;

                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this, "" + year + "/" + (month + 1) + "/" + dayOfMonth, 0).show();
                i= new Intent(date.this, time.class);
                startActivity(i);
                finish();
            }
        });
    }
}
