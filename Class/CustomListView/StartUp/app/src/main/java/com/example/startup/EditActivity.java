package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone, txtRoll, txtEmail;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        final Student student = (Student)intent.getSerializableExtra("Student");
        final int position = intent.getIntExtra("Position", -1);
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtRoll = findViewById(R.id.txtRoll);
        btnSave = findViewById(R.id.btnSave);

        txtName.setText(student.getName());
        txtRoll.setText(student.getRoll());
        txtPhone.setText(student.getPhone());
        txtEmail.setText(student.getEmail());
        txtAddress.setText(student.getAddress());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(txtName.getText().toString());
                student.setRoll(txtRoll.getText().toString());
                student.setAddress(txtAddress.getText().toString());
                student.setPhone(txtPhone.getText().toString());
                student.setEmail(txtEmail.getText().toString());

                Intent intent1= new Intent();
                intent1.putExtra("Student",student);
                intent1.putExtra("Position",position);
                setResult(200,intent1);
                finish();
            }
        });

    }
}
