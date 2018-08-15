package com.example.tuans.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tuans.hoclistview.R;

import java.util.List;

/**
 * Created by tuans on 14/03/2017.
 */

public class hinhAdapter extends ArrayAdapter<Integer>{

    Activity context;
    int resource;
    List<Integer> objects;
    public hinhAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Integer> objects) {
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
        ImageView img=(ImageView) row.findViewById(R.id.imgHinh1);
        img.setImageResource(this.objects.get(position));
        return row;
    }
}
