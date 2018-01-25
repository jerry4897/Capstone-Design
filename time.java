package com.example.cspclab.myapplication1;

/**
 * Created by R912 on 2017-12-21.
 */

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 * Created by R912 on 2017-12-21.
 */

public class time extends Activity implements RadioGroup.OnCheckedChangeListener {
    TextView tv;
    RadioGroup rg;
   orderActivity oa;
    String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        tv = (TextView) findViewById(R.id.tv);
        rg = (RadioGroup) findViewById(R.id.radio);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        name = ((orderActivity)orderActivity.oContext).name;
        if (group == rg) {
            if (checkedId == R.id.radio1) {
                tv.append("12시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"12시");
            } else if (group.getCheckedRadioButtonId() == R.id.radio2) {
                tv.setText("13시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"13시");
            } else if (group.getCheckedRadioButtonId() == R.id.radio3) {
                tv.setText("14시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"14시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio4) {
                tv.setText("15시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"15시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio5) {
                tv.setText("16시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"16시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio6) {
                tv.setText("17시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"17시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio7) {
                tv.setText("18시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"18시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio8) {
                tv.setText("19시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"19시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio9) {
                tv.setText("20시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"20시");
            }
            else if (group.getCheckedRadioButtonId() == R.id.radio10) {
                tv.setText("21시 예약입니다");
                ((MainActivity2)MainActivity2.mContext).update_time(name,"21시");
            }

            Intent j = new Intent(time.this,pay.class);
            startActivity(j);
            finish();
        }
    }
}
