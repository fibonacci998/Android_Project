package adapter;

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

import com.example.tuans.listviewadvance.R;

import java.util.List;

import model.DanhBa;

public class DanhBaAdapter extends ArrayAdapter<DanhBa>{
    //man hinh su dung layout
    Activity context;
    //layout cho tung dong
    int resource;
    //danh sach nguon du lieu hien thi len giao dien
    List<DanhBa> objects;

    public DanhBaAdapter(@NonNull Activity context, int resource, @NonNull List<DanhBa> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //build layout thanh code java
        LayoutInflater inflater=this.context.getLayoutInflater();
        //this.resource --> item.xml
        View row=inflater.inflate(this.resource,null);
        TextView txtTen=row.findViewById(R.id.txtTen);
        TextView txtPhone=row.findViewById(R.id.txtPhone);
        ImageButton btnCall=row.findViewById(R.id.btnCall);
        ImageButton btnSms=row.findViewById(R.id.btnSms);
        ImageButton btnMail=row.findViewById(R.id.btnMail);
        //tra ve danh ba hien tai muon ve
        final DanhBa danhba=this.objects.get(position);
        txtTen.setText(danhba.getTen());
        txtPhone.setText(danhba.getSdt());
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiXemChiTiet(danhba);
            }
        });
        return row;

    }

    private void xuLiXemChiTiet(DanhBa danhba) {
        Toast.makeText(this.context,"Ban dang chon: "+danhba.getTen(),Toast.LENGTH_LONG).show();
    }
}
