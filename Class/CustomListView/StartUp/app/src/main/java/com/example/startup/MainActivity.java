package com.example.startup;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Student> list = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        list.add("A");list.add("B");list.add("C");list.add("D");

        list.add(new Student("SE3203","A","49384","eiufb@gmail.com","hn",R.drawable.avatar1));
        list.add(new Student("SE2346","B","23544","rdgdv@gmail.com","hcm",R.drawable.avatar2));
        list.add(new Student("SE2323","C","43456","yffjft@gmail.com","hn",R.drawable.avatar1));
        list.add(new Student("SE4625","D","13435","urtrt@gmail.com","hcm",R.drawable.avartar4));
        list.add(new Student("SE7563","E","67567","qwqewq@gmail.com","hn",R.drawable.avatar1));

        listView = findViewById(R.id.listView);

//        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
        setAdapter();
    }

    public void setAdapter(){
        StudentAdapter adapter = new StudentAdapter(this, R.layout.student_layout,list);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            Student student = (Student)data.getSerializableExtra("Student");
            int position = data.getIntExtra("Position",-1);

            list.set(position, student);
            setAdapter();
        }
    }
}
