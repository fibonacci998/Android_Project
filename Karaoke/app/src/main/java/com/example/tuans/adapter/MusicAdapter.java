package com.example.tuans.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tuans.karaoke.R;
import com.example.tuans.model.Music;

import java.util.List;

/**
 * Created by tuans on 08/12/2017.
 */

public class MusicAdapter extends ArrayAdapter<Music>{
    Activity context;
    int resource;
    List<Music> objects;
    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtMa=row.findViewById(R.id.txtMa);
        TextView txtTen=row.findViewById(R.id.txtTen);
        TextView txtCasi=row.findViewById(R.id.txtCaSi);
        ImageButton btnLike=row.findViewById(R.id.btnLike);
        ImageButton btnDislike=row.findViewById(R.id.btnDislike);

        final Music music=this.objects.get(position);
        txtTen.setText(music.getTen());
        txtMa.setText(music.getMa());
        txtCasi.setText(music.getCaSi());
        if (!music.getThich()){
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }else{
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThich(music);
            }
        });
        return row;
    }

    private void xuLyThich(Music music) {
        music.setThich(true);

    }
}
