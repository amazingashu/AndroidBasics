package com.acrony.app1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.content_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)

       val txtstudent=findViewById<TextView>(R.id.nameofstudent)
        val seekBar= findViewById<SeekBar>(R.id.seekBar)

        val NameofStudent:String=intent.getStringExtra("Name")
        Toast.makeText(this,NameofStudent,Toast.LENGTH_LONG).show()

        txtstudent.setText(NameofStudent)

        val btnProg=findViewById<Button>(R.id.btnClick)

        val prob:ProgressBar=this.ShowProcess  //id of Progress Bar

        prob.visibility=View.GONE


        btnProg.setOnClickListener(){

         prob.visibility= View.VISIBLE
        }


        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {


                Toast.makeText(applicationContext, "seekbar progress: $progress", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_SHORT).show()

            }
        })



    }

}
