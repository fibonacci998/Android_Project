package com.example.tuans.tes1;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

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
