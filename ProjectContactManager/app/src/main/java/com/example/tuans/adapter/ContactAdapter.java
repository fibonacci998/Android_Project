package com.example.tuans.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tuans.model.Contact;
import com.example.tuans.projectcontactmanager.NhanTinSmsActivity;
import com.example.tuans.projectcontactmanager.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuans on 17/12/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtTen = row.findViewById(R.id.txtTen);
        TextView txtPhone = row.findViewById(R.id.txtPhone);
        ImageButton btnCall = row.findViewById(R.id.btnCall);
        ImageButton btnSms = row.findViewById(R.id.btnSms);
        ImageButton btnDelete = row.findViewById(R.id.btnDelete);
        final Contact contact = this.objects.get(position);
        txtTen.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyCall(contact);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLySms(contact);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa(contact);
            }
        });
        return row;
    }

    private void xuLyXoa(Contact contact) {
        this.remove(contact);
    }

    private void xuLySms(Contact contact) {
        Intent intent=new Intent(this.context, NhanTinSmsActivity.class);
        intent.putExtra("CONTACT",contact);
        this.context.startActivity(intent);
    }

    private void xuLyCall(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + contact.getPhone());
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }
}
