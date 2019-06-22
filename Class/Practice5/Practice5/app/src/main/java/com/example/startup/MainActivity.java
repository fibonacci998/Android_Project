package com.example.startup;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyHelper myHelper;
    private SQLiteDatabase db;
    ArrayAdapter<Employee> adapter;
    List<Employee> listEmployee = new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        loadDataToListView();
        setAdapterListView();
        initEvent();

    }
    void initComponent(){
        listView = findViewById(R.id.listView);
        myHelper = new MyHelper(getApplicationContext(),"hr");
    }
    void initEvent(){
        //register context menu
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee item = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("person",item);
                startActivity(intent);
            }
        });
    }
    void loadDataToListView(){
        db = myHelper.getReadableDatabase();
//        String select = "select * from person";
//        Cursor cursor = db.rawQuery(select,null);

        Cursor cursor = db.query("Employee",new String[]{"id","name","email","phone","address","salary"},null,null,null,null,null);
        listEmployee.clear();
        while (cursor.moveToNext()){
            Employee person = new Employee();
            person.setId(cursor.getInt(cursor.getColumnIndex("id")));
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            person.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            person.setSalary(cursor.getFloat(cursor.getColumnIndex("salary")));
            person.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            listEmployee.add(person);
        }
    }
    void setAdapterListView(){
        adapter = new ArrayAdapter<Employee>(this,android.R.layout.simple_list_item_1,listEmployee);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu_only,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,AddActivity.class);
        startActivityForResult(intent,100);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.employee_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd){
            Intent intent = new Intent(this,AddActivity.class);
            startActivityForResult(intent,100);
        }else if (item.getItemId() == R.id.menuDelete){


            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final int position = info.position;


            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Do you want to delete?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String delete = "DELETE FROM Employee WHERE id = ?";
                    db = myHelper.getWritableDatabase();
                    db.execSQL(delete, new String[]{String.valueOf(listEmployee.get(position).getId())});
                    listEmployee.remove(position);

                    loadDataToListView();
                    setAdapterListView();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            Employee person = (Employee)data.getSerializableExtra("person");
//            String insert = "insert into person(name,age,phone,address,salary) values (?,?,?,?,?)";
            db = myHelper.getWritableDatabase();
//            db.execSQL(insert, person.toArray());
            ContentValues values = new ContentValues();
            values.put("name",person.getName());
            values.put("email",person.getEmail());
            values.put("phone",person.getPhone());
            values.put("address",person.getAddress());
            values.put("salary",person.getSalary());
            db.insert("Employee",null,values);

            loadDataToListView();
            setAdapterListView();
        }
    }
}
