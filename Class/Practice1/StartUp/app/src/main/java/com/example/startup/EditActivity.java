package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Person person;
    EditText name;
    EditText age;
    EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        person = (Person) intent.getSerializableExtra("person");
        name = findViewById(R.id.edName);
        age = findViewById(R.id.etAge);
        address = findViewById(R.id.etAddress);
        name.setText(person.getName());
        age.setText(person.getAge()+"");
        address.setText(person.getAddress());

        Button btSave = findViewById(R.id.btSaveInfor);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setName(name.getText().toString());
                person.setAge(Integer.parseInt(age.getText().toString()));
                person.setAddress(address.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("personInfor", person);
                setResult(400,intent);
                finish();
            }
        });
    }
}
