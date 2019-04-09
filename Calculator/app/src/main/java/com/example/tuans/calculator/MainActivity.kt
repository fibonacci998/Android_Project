package com.example.tuans.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btNumberEvent(view:View){
        if (isNewOp){
            etShowNumber.setText("")
        }
        isNewOp=false
        val btSelected = view as Button
        var btClickValue:String=etShowNumber.text.toString()
        when (btSelected.id){
            bt0.id-> btClickValue+="0"
            bt1.id-> btClickValue+="1"
            bt2.id-> btClickValue+="2"
            bt3.id-> btClickValue+="3"
            bt4.id-> btClickValue+="4"
            bt5.id-> btClickValue+="5"
            bt6.id-> btClickValue+="6"
            bt7.id-> btClickValue+="7"
            bt8.id-> btClickValue+="8"
            bt9.id-> btClickValue+="9"
            btDot.id->{
                //todo: pervent adding more than 1 dot
                btClickValue+="."
            }
            btPlusMinus.id->{
                btClickValue="-"+btClickValue
            }
        }
        etShowNumber.setText(btClickValue)
    }
    var op="*"
    var oldNumber=""
    var isNewOp=true;

    fun btOpEvent(view:View){

        val btSelected = view as Button
        when (btSelected.id){
            btPlus.id-> op="+"
            btSub.id-> op="-"
            btDiv.id-> op="/"
            btMul.id-> op="*"
        }
        oldNumber=etShowNumber.text.toString();
        isNewOp=true
    }

    fun btEqualEvent(view: View){
        val newNuber=etShowNumber.text.toString()
        var finalNumber:Double?=null
        when(op){
            "*"->{
                finalNumber=oldNumber.toDouble() * newNuber.toDouble()
            }
            "/"->{
                finalNumber=oldNumber.toDouble() / newNuber.toDouble()
            }
            "+"->{
                finalNumber=oldNumber.toDouble() + newNuber.toDouble()
            }
            "-"->{
                finalNumber=oldNumber.toDouble() - newNuber.toDouble()
            }
        }
        etShowNumber.setText(finalNumber.toString())
        isNewOp=true
    }

    fun btPercentEvent(view:View){
        val number=etShowNumber.text.toString().toDouble()/100
        etShowNumber.setText(number.toString())
    }

    fun btClearEvent(view:View){
        etShowNumber.setText("0")
        isNewOp=true
    }
}
