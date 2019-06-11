package com.example.startup

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DbManager{
    val dbName = "MyNotes"
    val dbTable = "Notes"
    val colId = "ID"
    val colTitle = "Title"
    val colDes = "Description"
    val dbVersion = 1
    //create table
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+dbName+" ("+colId +" INTEGER PRIMARY KEY, "+
    colTitle+" TEXT, "+colDes+" TEXT)"

//    val sqlCreate="CREATE TABLE ${DbManager.dbTable} (" +
//            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
//            "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
//            "${FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"
    var sqlDB:SQLiteDatabase?=null


    constructor(context: Context){
        val db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelperNotes:SQLiteOpenHelper{
        var context:Context?=null

        constructor(context:Context):super(context,dbName,null,dbVersion){
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context,"Database is created",Toast.LENGTH_LONG).show()

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db!!.execSQL("drop table if exists "+dbTable)
            onCreate(db)
        }

    }

    fun Insert(value:ContentValues):Long{
        val ID = sqlDB!!.insert(dbTable,"",value)
        return ID
    }
}