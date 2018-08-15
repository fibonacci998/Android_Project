package com.example.tuans.karaoke;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.tuans.adapter.MusicAdapter;
import com.example.tuans.model.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiHatGoc;
    ArrayList<Music>dsBaiHatGoc;
    MusicAdapter adapterBaiHatGoc;

    ListView lvBaiHatYeuThich;
    ArrayList<Music>dsBaiHatYeuThich;
    ArrayAdapter adapterBaiHatYeuThich;

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equalsIgnoreCase("t1")){
                    xuLyHienThiBaiHatGoc();
                } else if (s.equalsIgnoreCase("t2"))
                    xuLiHienThiBaiHatYeuThich();
            }
        });
    }

    private void xuLiHienThiBaiHatYeuThich() {
        dsBaiHatYeuThich.clear();//xóa dữ liệu cũ
        for (Music music:dsBaiHatGoc){
            if (music.getThich()){
                dsBaiHatYeuThich.add(music);
            }
        }
        adapterBaiHatYeuThich.notifyDataSetChanged();
    }

    private void xuLyHienThiBaiHatGoc() {
        
    }

    private void addControls() {
        tabHost=findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.music));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.musicfavorite));
        tabHost.addTab(tab2);

        lvBaiHatGoc=findViewById(R.id.lvBaiHatGoc);
        dsBaiHatGoc=new ArrayList<>();
        adapterBaiHatGoc=new MusicAdapter(MainActivity.this,
                R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);

        lvBaiHatYeuThich=findViewById(R.id.lvBaiHatYeuThich);
        dsBaiHatYeuThich=new ArrayList<>();
        adapterBaiHatYeuThich=new MusicAdapter(MainActivity.this,
                R.layout.item,dsBaiHatYeuThich);
        lvBaiHatYeuThich.setAdapter(adapterBaiHatYeuThich);

        giaLapBaiHat();
    }

    private void giaLapBaiHat() {
        dsBaiHatGoc.add(new Music("bh1","Không yêu đừng nói","Ngọt ngào",false));
        dsBaiHatGoc.add(new Music("bh2","Lòng mẹ","Ngọc Sơn",true));
        dsBaiHatGoc.add(new Music("bh3","Góc trời","Tuán",false));
        dsBaiHatGoc.add(new Music("bh4","Ly cà ph","Ngọt ngào",false));
        adapterBaiHatGoc.notifyDataSetChanged();
    }
}
