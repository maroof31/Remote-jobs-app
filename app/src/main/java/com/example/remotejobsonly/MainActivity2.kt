package com.example.remotejobsonly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity2 : AppCompatActivity() {
    lateinit var jobsearchkeyword:String
    val jobsearchkeywordArray:Array<String> = arrayOf("ALL jobs","fullstack","frontend","backend","android developer","ios developer","python developer","java develper","UI/UX")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val spinner=findViewById<Spinner>(R.id.spinner_jobsearch)
        val arrayadapeter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,jobsearchkeywordArray)
        spinner.adapter=arrayadapeter
        spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position==0){
                    jobsearchkeyword=""
                }else{
                    jobsearchkeyword=jobsearchkeywordArray[position]
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity2, "nothing is selected", Toast.LENGTH_SHORT).show()
            }
        }
        val btngo=findViewById<Button>(R.id.btngo)
        btngo.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            intent.apply {
                putExtra("keyword",jobsearchkeyword)
            }
            startActivity(intent)
        }
    }
}