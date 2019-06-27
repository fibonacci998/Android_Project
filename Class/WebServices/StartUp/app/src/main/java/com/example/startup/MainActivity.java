package com.example.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyService myService = new MyService(this);
        myService.execute("http://jsonplaceholder.typicode.com/posts","1");
    }

    public void setMessage(String s){
        TextView tv = findViewById(R.id.textView);
        tv.setText(s);
    }
}
