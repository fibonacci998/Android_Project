package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtAddress, txtSalary,txtAge;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        txtName = findViewById(R.id.txtNameDisplay);
        txtPhone = findViewById(R.id.txtPhone);
        txtAge = findViewById(R.id.txtAge);
        txtAddress = findViewById(R.id.txtAddress);
        txtSalary = findViewById(R.id.txtSalary);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

    }
    void save(View view){
        Person person = new Person();
        person.setName(txtName.getText().toString());
        person.setAge(Integer.parseInt(txtAge.getText().toString()));
        person.setPhone(txtPhone.getText().toString());
        person.setSalary(Float.valueOf(txtSalary.getText().toString()));
        person.setAddress(txtAddress.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("person",person);
        setResult(200,intent);
        finish();
    }
}
