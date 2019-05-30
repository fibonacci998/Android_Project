package com.example.startup;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGender, etHobby, etBirthday, etBirthTime;
    Button btSubmit;

    String[] genders = {"Male","Female"};
    int posGender = 0;
    String[] hobbies = {"Soccer","Badmington","Swimming","Tennis","Golf","Bar"};
    boolean[] checkedItems = new boolean[hobbies.length];

    Calendar c = Calendar.getInstance();
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateComponent();
        generateEvent();
    }
    private void generateComponent(){
        etName = findViewById(R.id.etName);
        etGender = findViewById(R.id.etGender);
        etBirthday = findViewById(R.id.etDate);
        etBirthTime = findViewById(R.id.etBirthTime);
        etHobby = findViewById(R.id.etHobby);

        btSubmit = findViewById(R.id.btSubmit);
    }
    private void generateEvent(){
        etGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your gender");
                builder.setSingleChoiceItems(genders,0 , new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        posGender = which;
                    }
                });
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      etGender.setText(genders[posGender]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        etGender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select your gender");
                    builder.setSingleChoiceItems(genders,0 , new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            posGender = which;
                        }
                    });
                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int i = posGender;
                            String temp = genders[posGender];
                            etGender.setText(genders[posGender]);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        etHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your hobbies:");
                builder.setMultiChoiceItems(hobbies, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
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
                                sb.append(hobbies[i]);
                                sb.append(", ");
                            }
                        }
                        etHobby.setText(sb.toString());
                        //Toast.makeText(MainActivity.this,sb.toString()+" là những môn bạn thích",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Toast.makeText(MainActivity.this,dayOfMonth+"/"+(month+1)+"/"+year,Toast.LENGTH_LONG).show();
                        etBirthday.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                });
                datePickerDialog.show();
            }
        });


        etBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                                mHour = hourOfDay;
                                mMinute = minute;

                                etBirthTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Your information:");
                StringBuilder sb = new StringBuilder();
                sb.append("Name: "+etName.getText().toString()+"\n");
                sb.append("Gender: "+etGender.getText().toString()+"\n");
                sb.append("Hobbies: "+etHobby.getText().toString()+"\n");
                sb.append("Birthday: "+etBirthday.getText().toString()+"\n");
                sb.append("Birthtime: "+etBirthTime.getText().toString()+"\n");
                builder.setMessage(sb.toString());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
