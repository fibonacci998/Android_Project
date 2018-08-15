package com.example.tuans.hocassetvasharepreferent;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView txtFont;
    ListView lvFont;
    ArrayList<String>dsFont;
    ArrayAdapter<String>fontAdapter;
    String tenLuuTru="TrangThaiFont";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtFont=findViewById(R.id.txtFont);
        lvFont=findViewById(R.id.lvFont);
        dsFont=new ArrayList<>();
        fontAdapter=new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsFont);
        lvFont.setAdapter(fontAdapter);

        AssetManager assetManager=getAssets();
        try {
            String[]arrFontName=assetManager.list("font");
            dsFont.addAll(Arrays.asList(arrFontName));
            fontAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            Log.e("Loi font",e.toString());
        }

        SharedPreferences preferences=getSharedPreferences(tenLuuTru,MODE_PRIVATE);
        String font=preferences.getString("FONTCHU","");
        if (font.length()>0){
            Typeface typeface=Typeface.createFromAsset(getAssets(),font);
            txtFont.setTypeface(typeface);
        }
    }

    private void addEvents() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                xuLyDoiFontChu(i);
            }
        });
    }

    private void xuLyDoiFontChu(int i) {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font/"+dsFont.get(i));
        txtFont.setTypeface(typeface);

        SharedPreferences preferences=getSharedPreferences(tenLuuTru,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FONTCHU","font/"+dsFont.get(i));
        editor.commit();
    }
}
