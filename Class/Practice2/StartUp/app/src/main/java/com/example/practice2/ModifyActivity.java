package com.example.practice2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.startup.Person;
import com.example.startup.R;

public class ModifyActivity extends AppCompatActivity {

    EditText etName,etAddress,etPhone;
    Button btChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etName = findViewById(R.id.etName_modify);
        etAddress = findViewById(R.id.etAddress_modify);
        etPhone = findViewById(R.id.etPhone_modify);

        final Person personGet = (Person)getIntent().getSerializableExtra("person");
        etName.setText(personGet.getName());
        etAddress.setText(personGet.getAddress());
        etPhone.setText(personGet.getPhone());

        btChange = findViewById(R.id.btChange);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person personSend = personGet;
                personSend.setName(etName.getText().toString());
                personSend.setAddress(etAddress.getText().toString());
                personSend.setPhone(etPhone.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("person",personSend);
                setResult(300,intent);
                finish();
            }
        });
    }
}
