package com.example.tuans.tictactoy

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.random.Random
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        btRequest.setOnClickListener(View.OnClickListener {
            btRequestEvent(it)
        })
        btAccept.setOnClickListener(View.OnClickListener {
            btAcceptEvent(it)
        })
    }

    fun btRequestEvent(view:View){
        var userDemail = etEmail.text
    }
    fun  btAcceptEvent(view:View){

    }
    protected fun btClick(view:View){
        val btSelected = view as Button
        var cellId=0
        when(btSelected.id){
            R.id.bt1->cellId=1
            R.id.bt2->cellId=2
            R.id.bt3->cellId=3
            R.id.bt4->cellId=4
            R.id.bt5->cellId=5
            R.id.bt6->cellId=6
            R.id.bt7->cellId=7
            R.id.bt8->cellId=8
            R.id.bt9->cellId=9
        }
        //Toast.makeText(this,"Id: "+cellId,Toast.LENGTH_LONG).show()
        playGame(cellId,btSelected);
    }
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    var activePlayer=1
    fun playGame(cellId:Int,btSelected:Button){
        if (activePlayer==1){
            btSelected.text="X"
            btSelected.setBackgroundColor(Color.GREEN);
            player1.add(cellId);
            activePlayer=2;
            autoPlay()
        }else{
            btSelected.text="O"
            btSelected.setBackgroundColor(Color.BLUE);
            player2.add(cellId);
            activePlayer=1;
        }
        btSelected.isEnabled=false;
        checkWinner();
    }
    fun checkWinner(){
        var winner=-1;
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner=1;
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner=2;
        }

        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner=1;
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner=2;
        }

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner=1;
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2;
        }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner=1;
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner=2;
        }

        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1;
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2;
        }

        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1;
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner=2;
        }

        if (winner != -1){
            if (winner==1){
                Toast.makeText(this,"Player 1 win",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Player 2 win",Toast.LENGTH_LONG).show()
            }
        }
    }
    fun autoPlay(){
        var emptyCell=ArrayList<Int>()
        for (cellId:Int in 1..9){
            if ( !(player1.contains(cellId) || player2.contains(cellId))){
                emptyCell.add(cellId)
            }
        }
        val r= java.util.Random()
        val randIndex=r.nextInt(emptyCell.size-0)+0
        val cellId=emptyCell.get(randIndex)

        var btSelected:Button?
        when(cellId){
            1->btSelected=bt1
            2->btSelected=bt2
            3->btSelected=bt3
            4->btSelected=bt4
            5->btSelected=bt5
            6->btSelected=bt6
            7->btSelected=bt7
            8->btSelected=bt8
            9->btSelected=bt9
            else->btSelected=bt1
        }
        playGame(cellId,btSelected)
    }
}
