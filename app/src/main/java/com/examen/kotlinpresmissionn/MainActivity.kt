package com.examen.kotlinpresmissionn

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
// Diffutil receyvlerview is sample code is adding..
class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST_WRITE_FILE = 101
    private val MY_PERMISSIONS_REQUEST_CAMERA = 102
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPremission();
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
}


