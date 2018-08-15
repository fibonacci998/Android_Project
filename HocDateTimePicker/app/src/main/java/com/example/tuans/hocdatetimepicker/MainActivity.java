package com.example.tuans.hocdatetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    TextView txtDate,txtTime;
    ImageButton btnDate,btnTime;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat s1=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat s2=new SimpleDateFormat("HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiDatePicker();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiTimePicker();
            }
        });
    }

    private void xuLyHienThiTimePicker() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);

                txtTime.setText(s2.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(
                MainActivity.this,
                listener,
                calendar.get(calendar.HOUR_OF_DAY),
                calendar.get(calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void xuLyHienThiDatePicker() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);
                txtDate.setText(s1.format(calendar.getTime()));

            }
        };

        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,listener,
                calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void addControls() {
        txtDate= (TextView) findViewById(R.id.txtDate);
        txtTime= (TextView) findViewById(R.id.txtTime);
        btnDate= (ImageButton) findViewById(R.id.btnDate);
        btnTime= (ImageButton) findViewById(R.id.btnTime);
        calendar=Calendar.getInstance();
        txtDate.setText(s1.format(calendar.getTime()));
        txtTime.setText(s2.format(calendar.getTime()));
    }
}
