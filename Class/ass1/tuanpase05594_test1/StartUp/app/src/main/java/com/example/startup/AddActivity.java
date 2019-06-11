package com.example.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    Button btSubmitAdd;
    EditText etItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        btSubmitAdd = findViewById(R.id.btSubmitAdd);
        etItem = findViewById(R.id.etItem);

        btSubmitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("NewItem",etItem.getText().toString());
                setResult(200,intent1);
                finish();
            }
        });
    }
}
