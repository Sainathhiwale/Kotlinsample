package com.examen.kotlinpresmissionn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.examen.kotlinpresmissionn.R

class ParseSerializableActivity : AppCompatActivity() {
    private lateinit var tvBundleData:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_serializable)
        tvBundleData = findViewById(R.id.tvBundleData)

       // val bundle: Bundle? = intent.extras
        val data:String? = intent.getStringExtra("keyString")
        tvBundleData.text =data
    }
}