package com.example.startup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.practice2.AddActivity;
import com.example.practice2.ModifyActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btAdd, btDelete, btModify,btCall;
    List<Person> personList;
    RecyclerView recyclerView;
    ListView listView;
    Person personChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initEvent();
    }
    private void initEvent(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personChoose = (Person) listView.getAdapter().getItem(position);
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person selectedPerson = (Person) personChoose;
                for (Person temp: personList){
                    if (temp.getId() == selectedPerson.getId()){
                        personList.remove(temp);
                        break;
                    }
                }
                setAdapter();
            }
        });
        btModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person selectedPerson = (Person) personChoose;
                Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                intent.putExtra("person",selectedPerson);
                startActivityForResult(intent, 100);
            }
        });
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person selectedPerson = (Person) personChoose;

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.CALL_PHONE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                100);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    String phone = selectedPerson.getPhone();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            Person person = (Person)data.getSerializableExtra("person");
            personList.add(person);
            setAdapter();
        }else if (requestCode == 100 && resultCode == 300){
            Person person = (Person)data.getSerializableExtra("person");
            Person selectedPerson = (Person) listView.getSelectedItem();
            for (Person temp: personList){
                if (person.getId() == selectedPerson.getId()){
                    temp = person;
                    break;
                }
            }
            setAdapter();
        }
    }

    private void setAdapter(){
        ArrayAdapter<Person> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,personList);
        listView.setAdapter(adapter);
    }
    private void initComponent(){
        btAdd = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);
        btModify = findViewById(R.id.btModify);
        btCall = findViewById(R.id.btCall);
        //recyclerView = findViewById(R.id.recycleView);
        listView = findViewById(R.id.listView);

        personList = new ArrayList<Person>();
        personList.add(new Person(1,"A","A","113"));
        personList.add(new Person(2,"B","B","114"));
        personList.add(new Person(3,"C","C","115"));
        personList.add(new Person(4,"D","D","116"));
        personList.add(new Person(5,"E","E","117"));
        personList.add(new Person(6,"F","F","118"));
        personList.add(new Person(7,"G","G","119"));
        setAdapter();

    }
}
