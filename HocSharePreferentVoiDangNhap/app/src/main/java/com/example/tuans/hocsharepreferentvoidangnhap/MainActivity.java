package com.example.tuans.hocsharepreferentvoidangnhap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName,txtPassword;
    Button btnDangNhap,btnThoat;
    CheckBox chkLuu;
    String tenThongTinDangNhap="Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addColtrols();
        addEvents();
    }

    private void addColtrols() {
        txtUserName=findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnThoat=findViewById(R.id.btnThoat);
        chkLuu=findViewById(R.id.chkLuu);
    }

    private void addEvents() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences=getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName",txtUserName.getText().toString());
        editor.putString("Password",txtPassword.getText().toString());
        editor.putBoolean("SAVE",chkLuu.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences=getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        String userName=preferences.getString("UserName","");
        String password=preferences.getString("Password","");
        boolean save=preferences.getBoolean("SAVE",false);
        if (save){
            txtUserName.setText(userName);
            txtPassword.setText(password);
        }
    }
}
