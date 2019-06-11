package com.example.startup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Activity activity;
    private int layout;
    private List<Student> list;

    public StudentAdapter(Activity activity, int layout, List<Student> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView txtName, txtAddress;
        Button btnCall, btnEdit, btnDelete;
        ImageView imageView;

        if (convertView == null){
            convertView = activity.getLayoutInflater().inflate(layout, null);
            txtName = convertView.findViewById(R.id.txtName);
            txtAddress = convertView.findViewById(R.id.txtAddress);
            btnCall = convertView.findViewById(R.id.btnCall);
            btnEdit = convertView.findViewById(R.id.btnEdit);
            btnDelete = convertView.findViewById(R.id.btnDelete);
            imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(R.id.txtName, txtName);
            convertView.setTag(R.id.txtAddress, txtAddress);
            convertView.setTag(R.id.btnCall, btnCall);
            convertView.setTag(R.id.btnEdit, btnEdit);
            convertView.setTag(R.id.btnDelete, btnDelete);
            convertView.setTag(R.id.imageView, imageView);

        }else{
            txtName = (TextView)convertView.getTag(R.id.txtName);
            txtAddress = (TextView)convertView.getTag(R.id.txtAddress);
            btnCall = (Button) convertView.getTag(R.id.btnCall);
            btnDelete = (Button) convertView.getTag(R.id.btnDelete);
            btnEdit = (Button) convertView.getTag(R.id.btnEdit);
            imageView = (ImageView) convertView.getTag(R.id.imageView);
        }
        final Student student = list.get(position);
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        imageView.setImageResource(student.getResourceImage());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = student.getPhone();

                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.CALL_PHONE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CALL_PHONE},
                                123);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted
                    String uri = "tel:" + phone ;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    activity.startActivity(intent);
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Alert");
                builder.setMessage("Do you want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,EditActivity.class);
                intent.putExtra("Student",(Student)list.get(position));
                intent.putExtra("Position", position);
                activity.startActivityForResult(intent, 100);

            }
        });
        return convertView;
    }
}
