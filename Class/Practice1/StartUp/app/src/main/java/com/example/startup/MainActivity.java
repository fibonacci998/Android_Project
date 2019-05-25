package com.example.startup;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static com.example.startup.R.color.red;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    Spinner spinner2;
    List<Person> listPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layoutMain);


        Button btChange = findViewById(R.id.btnChangeColor);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivityForResult(intent, 100);
//                startActivity(intent);
//                finish();
            }
        });


        listPerson = new ArrayList<>();
        listPerson.add(new Person(1,"A",20,"1A"));
        listPerson.add(new Person(2,"B",20,"2B"));
        listPerson.add(new Person(3,"C",20,"3C"));
        listPerson.add(new Person(4,"D",20,"4D"));
        ArrayAdapter<Person> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listPerson);

        spinner2= findViewById(R.id.spinner2);

        spinner2.setAdapter(adapter);
        Button btEdit = findViewById(R.id.btnEdit);
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                Person personChoose = (Person) spinner2.getSelectedItem();
                intent.putExtra("person", personChoose);
                startActivityForResult(intent, 300);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode ==200){
            String color = data.getStringExtra("Color");

            if (color != null){
                if (color.equals("RED")){
                    layout.setBackgroundResource(R.color.red);
                }else if (color.equals("GREEN")){
                    layout.setBackgroundResource(R.color.green);
                }else if (color.equals("BLUE")){
                    layout.setBackgroundResource(R.color.blue);
                }
            }
        }
        if (requestCode == 300 && resultCode ==400){
            Person temp = (Person)data.getSerializableExtra("personInfor");
            for (int i = 0;i<listPerson.size();i++){
                if (listPerson.get(i).getId() == temp.getId()){
                    listPerson.get(i).setName(temp.getName());
                    listPerson.get(i).setAge(temp.getAge());
                    listPerson.get(i).setAddress(temp.getAddress());
                }
            }
            ArrayAdapter<Person> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listPerson);
            spinner2.setAdapter(adapter);
        }
    }
}
