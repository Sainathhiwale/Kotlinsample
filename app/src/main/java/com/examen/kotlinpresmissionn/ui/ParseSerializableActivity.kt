package com.examen.kotlinpresmissionn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.examen.kotlinpresmissionn.R
import com.examen.kotlinpresmissionn.model.Blog
import com.examen.kotlinpresmissionn.model.Student

class ParseSerializableActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvBundleData:TextView
    private lateinit var tvParseData:TextView
    private lateinit var btParse:Button
    private lateinit var btSerializable:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_serializable)
        tvBundleData = findViewById(R.id.tvBundleData)
        tvParseData = findViewById(R.id.tvParseData)
        btParse = findViewById(R.id.btParse)
        btSerializable = findViewById(R.id.btSerializable)
        btParse.setOnClickListener(this)
        btSerializable.setOnClickListener(this)

      //  val bundle: Bundle? = intent.extras
        val data:String? = intent.getStringExtra("keyString")
        if (data!=null){
            tvBundleData.text =data
        }
        //parseable data object
        val stData: Student? = intent.getParcelableExtra<Student>("studentData")
        if (stData!=null){
            tvParseData.text = "Name:"+ stData.name +"ID:"+ stData.id +"address:"+stData.address +"phone:"+stData.phone
        }
        // serializable data object
        val blog : Blog? = intent.getSerializableExtra("blogdata") as Blog?
        if (blog!=null){
            tvParseData.text = "Name:"+blog.name +"\n year:"+blog.year
        }

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btParse ->{
                parseData();
            }
            R.id.btSerializable ->{
                serializableData();
            }
        }
    }

    private fun serializableData() {
        val blog : Blog? = intent.getSerializableExtra("blogdata") as Blog?
        if (blog!=null){
            tvParseData.text = "Name:"+blog.name +"\n year:"+blog.year
        }
    }

    private fun parseData() {
        val stData: Student? = intent.getParcelableExtra<Student>("studentData")
        if (stData!=null){
            tvParseData.text = "Name:"+stData?.name +"\n ID:"+stData?.id +"\n address:"+stData?.address +"\n phone:"+stData?.phone
        }
    }
}