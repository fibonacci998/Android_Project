package com.example.practice2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.startup.Person;
import com.example.startup.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText etId = findViewById(R.id.etId);
        final EditText etName = findViewById(R.id.etName_modify);
        final EditText etAddress = findViewById(R.id.etAddress_modify);
        final EditText etPhone = findViewById(R.id.etPhone);
        Button btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(Integer.valueOf(etId.getText().toString()),
                        etName.getText().toString(),etAddress.getText().toString(), etPhone.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("person",person);
                setResult(200,intent);
                finish();
            }
        });
    }
}
