package com.examen.kotlinpresmissionn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examen.kotlinpresmissionn.adapter.UserAdapter
import com.examen.kotlinpresmissionn.model.Users
import com.examen.kotlinpresmissionn.ui.ParseSerializableActivity

//https://howtodoandroid.com/update-android-recyclerview-using-diffutil/
class RecyclerviewDiffutilActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var users: List<Users>
    private val TAG = "RecyclerviewDiffutilActivity"
    private lateinit var rview:RecyclerView
    private lateinit var btSortAscending:Button
    private lateinit var btSortdescending:Button
    private lateinit var btParseActivity:Button
    private lateinit var sortedList:List<Users>

    private lateinit var layoutManager: LinearLayoutManager

    private var userAdapter:UserAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_diffutil)
        val data:String= intent.getStringExtra("data").toString()
        val info :String = intent.getStringExtra("info").toString()
        Log.d("RecyclerviewActivity", "onCreate: "+info +"data::"+data)
        rview = findViewById(R.id.rview)
        btSortAscending = findViewById(R.id.btSortAscending)
        btSortdescending = findViewById(R.id.btSortdescending)
        btParseActivity = findViewById(R.id.btParseActivity)
        initList()
        initListener();
    }
    private fun initListener(){
        btSortAscending.setOnClickListener(this)
        btSortdescending.setOnClickListener(this)
        btParseActivity.setOnClickListener(this)
    }
    private fun initList(){
         users = listOf<Users>(
            Users(1,"sainath","Pune","image1"),
            Users(2,"ziya","Gangapur","image2"),
            Users(3,"Ravi","Pune","image3"),
            Users(4,"Bhagwan","Pune","image4"),
            Users(5,"Pankaj","mumbai","image5")
        )
        rview.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(users.toMutableList())
        rview.adapter = userAdapter
    }

    override fun onClick(view: View?) {
       when(view?.id){
           R.id.btSortAscending ->{
               sortAscending()
           }
           R.id.btSortdescending->{
             sortDescnding()
           }
           R.id.btParseActivity ->{
               val intent = Intent(this,ParseSerializableActivity::class.java)
               intent.putExtra("keyString", "Androidly String data")
               startActivity(intent)
           }
       }
    }

    private fun sortAscending() {
         sortedList  = users.sortedBy { it.username }
        userAdapter?.setUserList(sortedList)


    }
    private fun sortDescnding(){
         sortedList = users.sortedByDescending { it.username }
        userAdapter?.setUserList(sortedList)
    }
}