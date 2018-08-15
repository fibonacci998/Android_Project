package com.example.tuans.adapter;

import android.app.Activity;
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
 * Created by tuans on 07/12/2017.
 */

public class DanhBaAdapter extends ArrayAdapter<DanhBa>{
    Activity context;//màn hình sử dụng layout
    int resource;//layout từng dòng muốn hiển thị item.xml
    List<DanhBa> objects;//danh sách nguồn dữ liệu muốn hiển thị lên giao diện

    public DanhBaAdapter(Activity context, int resource, List<DanhBa> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //lớp build layout thành code java
        LayoutInflater temp=this.context.getLayoutInflater();
        View row=temp.inflate(this.resource,null);

        TextView txtTen=row.findViewById(R.id.txtTen);
        TextView txtPhone=row.findViewById(R.id.txtPhone);

        ImageButton btnCall=row.findViewById(R.id.btnCall);
        ImageButton btnSms=row.findViewById(R.id.btnSms);
        ImageButton btnMail=row.findViewById(R.id.btnMail);

        final DanhBa db=this.objects.get(position);

        txtTen.setText(db.getTen());
        txtPhone.setText(db.getChuoi());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyBamCall(db);
            }
        });
        return row;
    }

    private void xuLyBamCall(DanhBa db) {
        Toast.makeText(this.context,"Bạn call cho "+db.getTen(),Toast.LENGTH_LONG).show();
    }
}
