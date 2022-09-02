package com.examen.kotlinpresmissionn

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.examen.kotlinpresmissionn.ui.LoginActivity

// Diffutil receyvlerview is sample code is adding..
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val MY_PERMISSIONS_REQUEST_WRITE_FILE = 101
    private val MY_PERMISSIONS_REQUEST_CAMERA = 102
    private val TAG = "MainActivity"
    private lateinit var btNext:Button
    private lateinit var btNextLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btNext = findViewById(R.id.btNext)
        btNextLogin = findViewById(R.id.btNextLogin)
        btNext.setOnClickListener(this)
        btNextLogin.setOnClickListener(this)
        initPremission()
    }

    private fun initPremission() {
        if (checkPremission(Manifest.permission.CAMERA)){
         Log.d(TAG,"Granted Premission")

        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                         AlertDialog.Builder(this)
                             .setMessage("Need camera permission to capture image. Please provide permission to access your camera.")
                             .setPositiveButton("Ok"){ dialogInterface,i->
                                 dialogInterface.dismiss()
                                 ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),MY_PERMISSIONS_REQUEST_CAMERA)

                             }
                             .setNegativeButton("Cancel") { dialogInterface, i ->
                                 dialogInterface.dismiss()
                             }
                             .create()
                             .show();
                }else{
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA,Manifest
                        .permission.WRITE_EXTERNAL_STORAGE),MY_PERMISSIONS_REQUEST_CAMERA)
                }
            }
        }
    }

    private fun checkPremission(permission: String): Boolean {
      return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "premission granted!", Toast.LENGTH_LONG).show()

            }else {
               // ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),MY_PERMISSIONS_REQUEST_CAMERA)

                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onClick(view: View?) {
        if (view?.id ==R.id.btNext){
            val intent = Intent(this,RecyclerviewDiffutilActivity::class.java)
            intent.putExtra("data","sample test")
            intent.putExtra("info","sample info test")
            startActivity(intent)
        }
        if(view?.id==R.id.btNextLogin){
            val intent = Intent(this,LoginActivity::class.java)
           // intent.putExtra("DATA","Intent data to pass the Login activity");
            startActivity(intent)
        }
    }
}


