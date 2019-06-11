package com.example.startup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAddNew;
    private MyHelper myHelper;
    private SQLiteDatabase db;
    List<Person> listPerson = new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();

        loadDataToListView();
        setAdapterListView();

        initEvent();
    }
    private void initComponent(){
        listView = findViewById(R.id.listView);
        btnAddNew = findViewById(R.id.btnAddNew);
        myHelper = new MyHelper(getApplicationContext(),"mydatabase.db");

    }
    private void initEvent(){
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == 200){
            Person person = (Person)data.getSerializableExtra("person");
            String insert = "insert into person(name,age,phone,address,salary) values (?,?,?,?,?)";
            db = myHelper.getWritableDatabase();
            db.execSQL(insert, person.toArray());

            loadDataToListView();
            setAdapterListView();
        }
        if (requestCode == 100 && resultCode == 300){
            Person person = (Person)data.getSerializableExtra("person");
            String update ="UPDATE person " +
                    "SET name=?, age=?,phone=?,address=?,salary=? " +
                    "WHERE id=?";
            db = myHelper.getWritableDatabase();
            db.execSQL(update, person.toArrayUpdate());

            loadDataToListView();
            setAdapterListView();
        }
    }

    public void addNew(View view){
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent,100);
    }
    private void loadDataToListView(){
        db = myHelper.getReadableDatabase();
        String select = "select * from person";
        Cursor cursor = db.rawQuery(select,null);
        listPerson.clear();
        while (cursor.moveToNext()){
            Person person = new Person();
            person.setId(cursor.getInt(cursor.getColumnIndex("id")));
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            person.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            person.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            person.setSalary(cursor.getFloat(cursor.getColumnIndex("salary")));
            listPerson.add(person);
        }

    }
    private void setAdapterListView(){
        PersonAdapter adapter = new PersonAdapter(this,R.layout.person_layout,listPerson,myHelper,db);
        listView.setAdapter(adapter);
    }
}
