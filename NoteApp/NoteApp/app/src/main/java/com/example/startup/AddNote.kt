package com.example.startup

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import java.lang.Exception

class AddNote : AppCompatActivity() {
    val dbTable="Notes"
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        try{
            var bundle:Bundle = intent.extras
            id = bundle.getInt("ID",0)
            if (id != 0){
                etTitle.setText(bundle.getString("name").toString())
                etDes.setText(bundle.getString("des").toString())
            }

        }catch (ex:Exception){

        }
    }
    fun buAdd(view:View){
        var dbManager=DbManager(this)
        var values = ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())

        if (id==0){
            val ID = dbManager.Insert(values)
            if (ID>0){
                Toast.makeText(this,"Note is added",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Cannot add note",Toast.LENGTH_LONG).show()
            }
        }else{
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArgs)
            if (ID>0){
                Toast.makeText(this,"Note is edited",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Cannot edit note",Toast.LENGTH_LONG).show()
            }

        }


    }
}
