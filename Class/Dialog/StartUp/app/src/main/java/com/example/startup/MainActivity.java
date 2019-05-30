package com.example.startup;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.net.MailTo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int pos = 0;
    String[] subject = {"Java","C++","Android",".Net","PHP","Objective C","Kotlin"};
    boolean[] checkedItems = new boolean[subject.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btShow = findViewById(R.id.btnShowAlert);
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Thông báo");
//                builder.setMessage("Bạn có thích học không?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"Gôd",Toast.LENGTH_LONG).show();
//                    }
//                });
//                builder.setNegativeButton("Ko", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"Bad",Toast.LENGTH_LONG).show();
//                    }
//                });

                builder.setTitle("Bạn thích học gì?");

//                builder.setItems(subject, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,subject[which]+" là môn bạn thích",Toast.LENGTH_LONG).show();
//                    }
//                });

//                builder.setSingleChoiceItems(subject,0 , new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        pos = which;
//                    }
//                });
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,subject[pos]+"Là môn bạn thích",Toast.LENGTH_LONG).show();
//                    }
//                });

                builder.setMultiChoiceItems(subject, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb=new StringBuilder();
                        for (int i=0;i<checkedItems.length;i++){
                            if (checkedItems[i]){
                                sb.append(subject[i]);
                                sb.append(", ");
                            }
                        }
                        Toast.makeText(MainActivity.this,sb.toString()+" là những môn bạn thích",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        Button btSelectDate = findViewById(R.id.btSelectDate);
        btSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(MainActivity.this,"Day: "+dayOfMonth+",Month:"+month+", Year:"+year,Toast.LENGTH_LONG).show();
                    }
                });
                datePickerDialog.show();
            }
        });
    }
}
