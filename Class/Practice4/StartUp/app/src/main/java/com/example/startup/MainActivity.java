package com.example.startup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button btAdd,btDelete;
    List<String> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initEvent();
    }
    private void setAdapterSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listData);
        spinner.setAdapter(adapter);
    }
    private void initComponent(){
        spinner = findViewById(R.id.spSpinner);
        btAdd = findViewById(R.id.btAdd);
        btDelete = findViewById(R.id.btDelete);

        listData = new ArrayList<>();
        listData.add("A");
        listData.add("B");
        listData.add("C");
        setAdapterSpinner();
    }
    private void initEvent(){
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
                if (spinner.getSelectedItem()==null){
                    Toast.makeText(MainActivity.this, "You must select 1 item", Toast.LENGTH_SHORT).show();
                }else{
                    final String itemChoose = (String)spinner.getSelectedItem();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("Do you want to delete?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            for (int i = 0;i<listData.size();i++){
//                                if (listData.get(i).equals(itemChoose)){
//                                    listData.remove(i);
//                                    break;
//                                }
//                            }
                            listData.remove(spinner.getSelectedItemPosition());
//                            listData.remove(itemChoose);
                            setAdapterSpinner();
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
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode ==200){
            String newItem = data.getStringExtra("NewItem");
            listData.add(newItem);
            setAdapterSpinner();
        }
    }
}
