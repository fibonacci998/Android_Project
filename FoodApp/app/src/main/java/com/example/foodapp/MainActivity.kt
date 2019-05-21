package com.example.foodapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    val listOfFoods = ArrayList<Food>()
    var adapter:FoodAdapter ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load foods
        listOfFoods.add(Food("Coffee","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.espresso))
        listOfFoods.add(Food("French fries","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.french_fries))
        listOfFoods.add(Food("Honey","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.honey))
        listOfFoods.add(Food("Strawberry ice cream","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.strawberry_ice_cream))
        listOfFoods.add(Food("Sugar cubes","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",R.drawable.sugar_cubes))
        adapter = FoodAdapter(this, listOfFoods)

        gvListFood.adapter = adapter
    }

    class FoodAdapter:BaseAdapter{
        var listOfFood = ArrayList<Food>()
        var context:Context?=null
        constructor(context:Context, listOfFood:ArrayList<Food>):super(){
            this.context = context
            this.listOfFood = listOfFood
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var food = this.listOfFood[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_ticket, null)
            foodView.ivFood.setImageResource(food.image!!)
            foodView.ivFood.setOnClickListener{
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name",food.name)
                intent.putExtra("des", food.des)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }
            foodView.tvName.text= food.name!!
            return foodView
        }

        override fun getItem(position: Int): Any {
            return listOfFood[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfFood.size
        }

    }
}
