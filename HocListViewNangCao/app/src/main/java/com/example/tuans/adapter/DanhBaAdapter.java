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
import android.widget.Toast;

import com.example.tuans.hoclistviewnangcao.R;
import com.example.tuans.model.DanhBa;

import java.util.List;

/**
 * Created by tuans on 12/03/2017.
 */

public class DanhBaAdapter extends ArrayAdapter<DanhBa> {
    //đối số 1 màn hình sử dụng layout
    Activity context;
    //layout từng dòng hiển thị
    int resource;
    //danh sách nguồn dữ liệu hiển thị
    List<DanhBa> objects;
    public DanhBaAdapter( Activity context,  int resource,  List<DanhBa> objects) {
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
        TextView txtTen= (TextView) row.findViewById(R.id.txtTen);
        TextView txtPhone= (TextView) row.findViewById(R.id.txtPhone);
        ImageButton btnCall= (ImageButton) row.findViewById(R.id.btnCall);
        ImageButton btnSms= (ImageButton) row.findViewById(R.id.btnSms);
        ImageButton btnMail= (ImageButton) row.findViewById(R.id.btnMail);
        //trả về danh bạ hiện tại muốn vẽ
        final DanhBa db=this.objects.get(position);
        txtTen.setText(db.getTen());
        txtPhone.setText(db.getPhone());

        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXemChiTiet(db);
            }
        });

        return row;
    }

    private void xuLyXemChiTiet(DanhBa db) {
        Toast.makeText(this.context,"wtf"+db.getTen(),Toast.LENGTH_LONG).show();
    }


}
