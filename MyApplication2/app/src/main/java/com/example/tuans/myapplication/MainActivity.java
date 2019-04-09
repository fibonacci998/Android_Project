package com.example.tuans.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Cach 2
    Button btnReset, btnCheckLogin2;
    EditText edtUsr2, edtPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnCheckLogin2 = (Button) findViewById(R.id.btnCheckLogin2);
        edtUsr2 = (EditText) findViewById(R.id.edtUsr2);
        edtPass2 = (EditText) findViewById(R.id.edtPass2);

        btnCheckLogin2.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    private boolean checkLogin(String u, String p) {
        if(u.equals("a") && p.equals("a")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnCheckLogin2:
                String username = edtUsr2.getText().toString();
                String password = edtPass2.getText().toString();
                if (username == "" || password == "") {
                    Toast.makeText(getApplicationContext(),"You need to enter U and P", Toast.LENGTH_LONG).show();
                    return;
                } else if (checkLogin(username, password)) {
                    Toast.makeText(getApplicationContext(),"Login successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this,SecondActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),"Username or password is incorrect", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.btnReset:
                edtPass2.setText("");
                edtUsr2.setText("");
                edtUsr2.setFocusable(true);
                edtUsr2.requestFocus();
                break;

            default:
        }
    }
}