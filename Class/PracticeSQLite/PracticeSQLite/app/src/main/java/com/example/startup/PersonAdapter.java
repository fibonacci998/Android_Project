package com.example.startup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Activity activity;
    private int layout;
    private List<Person> list;
    private MyHelper myHelper;
    private SQLiteDatabase db;

    public PersonAdapter(Activity activity, int layout, List<Person> list,MyHelper myHelper, SQLiteDatabase db) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
        this.myHelper = myHelper;
        this.db = db;
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
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView txtName;
        Button btnEdit, btnDelete;
        if (convertView == null){
            convertView = activity.getLayoutInflater().inflate(layout,null);
            txtName = convertView.findViewById(R.id.txtNameDisplay);
            btnDelete = convertView.findViewById(R.id.btnDelete);
            btnEdit = convertView.findViewById(R.id.btnEdit);

            convertView.setTag(R.id.txtNameDisplay, txtName);
            convertView.setTag(R.id.btnDelete, btnDelete);
            convertView.setTag(R.id.btnEdit, btnEdit);
        }else{
            txtName = (TextView)convertView.getTag(R.id.txtNameDisplay);
            btnDelete = (Button)convertView.getTag(R.id.btnDelete);
            btnEdit = (Button)convertView.getTag(R.id.btnEdit);
        }
        Person person = list.get(position);
        txtName.setText(person.getName());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditActivity.class);
                intent.putExtra("person",(Person)list.get(position));
                activity.startActivityForResult(intent,100);
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
                        String delete = "DELETE FROM person WHERE id = ?";
                        db = myHelper.getWritableDatabase();
                        db.execSQL(delete, new String[]{String.valueOf(list.get(position).getId())});
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
        return convertView;
    }
}
