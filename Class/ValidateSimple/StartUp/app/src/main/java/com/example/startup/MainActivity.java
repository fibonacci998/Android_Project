package com.example.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.TextValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.edUsername);
        final EditText password = findViewById(R.id.edPassword);
        final EditText repassword = findViewById(R.id.edRepassword);

        password.addTextChangedListener(new TextValidator(password) {
            boolean checkStringWithRegex(String text, String regex){
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(text);
                return matcher.find();
            }
            @Override
            public void validate(TextView textView, String text) {
                if (text.length()<8){
                    textView.setError("Password must be at least 8 character");
                    return;
                }
                String regex = "[^0-9a-zA-Z]";
                if (!checkStringWithRegex(text, regex)){
                    textView.setError("Password must have at least 1 special character");
                    return;
                }

                String regex1= "[0-9]";
                if (!checkStringWithRegex(text, regex1)){
                    textView.setError("Password must have number");
                    return;
                }
                String regex2= "[a-z]";
                if (!checkStringWithRegex(text, regex2)){
                    textView.setError("Password must have lower case");
                    return;
                }
                String regex3= "[A-Z]";
                if (!checkStringWithRegex(text, regex3)){
                    textView.setError("Password must have upper case");
                    return;
                }
            }
        });
        repassword.addTextChangedListener(new TextValidator(repassword) {
            @Override
            public void validate(TextView textView, String text) {
                String passwordString = password.getText().toString();
                if (!passwordString.equals(text)){
                    textView.setError("Re-password must be the same as password");
                }
            }
        });
        Button btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getError() == null && repassword.getError() == null){
                    Toast.makeText(MainActivity.this, "Sign up completed", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Sign up uncompleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
