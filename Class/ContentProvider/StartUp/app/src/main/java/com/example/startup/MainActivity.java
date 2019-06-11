package com.example.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin;
    CheckBox cbRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initEvent();
    }
    void initComponent(){
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
        btnLogin = findViewById(R.id.btLogin);
        cbRemember = findViewById(R.id.cbRemember);
        SharedPreferences pref = getSharedPreferences("account", Context.MODE_PRIVATE);
        String username = pref.getString("username","");
        String password = pref.getString("password","");
        txtUsername.setText(username);
        txtPassword.setText(password);
    }
    void initEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                if (username.equals("admin") && password.equals("123")){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    SharedPreferences pref = getSharedPreferences("account", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    if (cbRemember.isChecked()){
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.commit();
                    }else{
                        editor.remove("username");
                        editor.remove("password");
                        editor.commit();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Login Fail",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
