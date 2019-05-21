package com.example.zooapp

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboon","Baboon lives in big place with tree",R.drawable.baboon,false))
        listOfAnimals.add(Animal("Bulldog","Bulldog lives in big place with tree",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Panda lives in big place with tree",R.drawable.panda,true))
        listOfAnimals.add(Animal("Swallow bird","Swallow bird lives in big place with tree",R.drawable.swallow_bird,false))
        listOfAnimals.add(Animal("White tiger","White tiger lives in big place with tree",R.drawable.white_tiger,true))
        listOfAnimals.add(Animal("Zebra","Zebra lives in big place with tree",R.drawable.zebra,false))

        adapter = AnimalsAdapter(this, listOfAnimals)
        lvListAnimal.adapter = adapter
    }

    fun delete(index: Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()

    }
    fun add(index: Int){
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }
    inner class AnimalsAdapter:BaseAdapter{
        var listOfAnimals = ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context,listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context= context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]
            if (animal.isKilled==true){
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimal.setImageResource(animal.image!!)
                myView.ivAnimal.setOnClickListener{
//                    val intent = Intent(context,AnimalInfo::class.java)
//                    intent.putExtra("name",animal.name)
//                    intent.putExtra("des",animal.des)
//                    intent.putExtra("image",animal.image!!)
//                    context!!.startActivity(intent)
                    delete(position)
                }
                return myView
            }else{
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimal.setImageResource(animal.image!!)
                myView.ivAnimal.setOnClickListener{
                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name)
                    intent.putExtra("des",animal.des)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
