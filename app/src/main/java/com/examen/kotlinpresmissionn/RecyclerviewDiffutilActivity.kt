package com.examen.kotlinpresmissionn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examen.kotlinpresmissionn.adapter.UserAdapter
import com.examen.kotlinpresmissionn.model.Users

class RecyclerviewDiffutilActivity : AppCompatActivity() {
    private val TAG = "RecyclerviewDiffutilActivity"
    private lateinit var rview:RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var userAdapter:UserAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_diffutil)
        val data:String= intent.getStringExtra("data",).toString()
        val info :String = intent.getStringExtra("info").toString()
        Log.d("RecyclerviewActivity", "onCreate: "+info +"data::"+data)
        rview = findViewById(R.id.rview)
        initList()


    }
    private fun initList(){
        val user = listOf<Users>(
            Users(1,"sainath","Pune","image1"),
            Users(1,"sai","Pune","image2"),
            Users(1,"Ravi","Pune","image3"),
            Users(1,"Bhagwan","Pune","image4"),
            Users(1,"Pankaj","mumbai","image5")
        )
        rview.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(user.toMutableList())
        rview.adapter = userAdapter
    }
}