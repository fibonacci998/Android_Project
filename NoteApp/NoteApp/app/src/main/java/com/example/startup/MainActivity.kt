package com.example.startup

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DialogTitle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var listNotes=ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        listNotes.add(Note(1,"AAAAA","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"))
//        listNotes.add(Note(2,"BBBBB","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"))
//        listNotes.add(Note(3,"CCCCC","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"))
//        listNotes.add(Note(4,"DDDDD","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"))
//        listNotes.add(Note(5,"EEEEE","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"))




        //load from db
        LoadQuery("%")
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }
    fun LoadQuery(title: String){
        var dbManager = DbManager(this)
        val projection = arrayOf("ID","Title","Description")
        val selectArgs = arrayOf(title)
        val cursor = dbManager.Query(projection,"Title like ?",selectArgs,"Title")
        listNotes.clear()
        if (cursor.moveToFirst()){
            do {
                val id =cursor.getInt(cursor.getColumnIndex("ID"))
                val title =cursor.getString(cursor.getColumnIndex("Title"))
                val des =cursor.getString(cursor.getColumnIndex("Description"))
                listNotes.add(Note(id,title,des))
            }while (cursor.moveToNext())
        }

        var myNoteAdapter = MyNoteAdapter(this,listNotes)
        lvNotes.adapter = myNoteAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        var sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                LoadQuery("%"+newText+"%")
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!=null)
        when (item.itemId){
            R.id.addNote->{
                //add note
                var intent = Intent(this,AddNote::class.java)
                startActivity(intent)
                var i =1
                LoadQuery("%")
            }
            R.id.app_bar_search->{
                //search note
            }
        }
        LoadQuery("%")
        return super.onOptionsItemSelected(item)
    }
    inner class MyNoteAdapter:BaseAdapter{

        var context:Context?=null
        var listNotesAdapter=ArrayList<Note>()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket,null)
            var myNote = listNotesAdapter[position]
            myView.tvTitle.text=myNote.noteName
            myView.tvDes.text=myNote.noteDes
            myView.ibDelete.setOnClickListener(View.OnClickListener {
                var dbManager = DbManager(this.context!!)
                val selectionArgs = arrayOf(myNote.noteID.toString())
                dbManager.Delete("ID=?", selectionArgs)
                LoadQuery("%")
            })
            myView.ibEdit.setOnClickListener(View.OnClickListener {
                GoToUpdate(myNote)
            })
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotes.get(position)
        }

        override fun getItemId(position: Int): Long {
            return  position.toLong()
        }

        override fun getCount(): Int {
            return listNotes.size
        }


        constructor(context:Context, listNotesAdapter:ArrayList<Note>):super(){
            this.listNotesAdapter = listNotesAdapter
            this.context = context;
        }

    }
    fun GoToUpdate(note:Note){
        var intent = Intent(this, AddNote::class.java)
        intent.putExtra("ID",note.noteID)
        intent.putExtra("name",note.noteName)
        intent.putExtra("des",note.noteDes)
        startActivity(intent)
    }
}
