package com.example.startup

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNote : AppCompatActivity() {
    val dbTable="Notes"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }
    fun buAdd(view:View){
        var dbManager=DbManager(this)
        var values = ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())
        val ID = dbManager.Insert(values)
        if (ID>0){
            Toast.makeText(this,"Note is added",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Cannot add note",Toast.LENGTH_LONG).show()
        }
    }
}
