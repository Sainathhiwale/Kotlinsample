package com.examen.kotlinpresmissionn.ui

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.examen.kotlinpresmissionn.R
import com.examen.kotlinpresmissionn.model.login.UserLogin
import com.examen.kotlinpresmissionn.model.network_retrofit.RetrofitInstance
import com.examen.kotlinpresmissionn.model.network_retrofit.ServiceAPIInterface
import com.examen.kotlinpresmissionn.ui.broadcast.ConnectivityReceiver
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener  {
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var etUserName:EditText
    private lateinit var etPassword:EditText
    private lateinit var bt_Login:Button
    private lateinit var cont_login:ConstraintLayout
    private  var userName :String? =null
    private var password :String? = null
    private var snackBar: Snackbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(this,cont_login,"You are offline",Snackbar.LENGTH_SHORT)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.show()

        } else {
            snackBar?.dismiss()
        }
    }
    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun initView() {
        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        bt_Login = findViewById(R.id.btNextLogin)
        cont_login = findViewById(R.id.cont_login)
        bt_Login.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
       when(view?.id){
           R.id.btNextLogin ->{
                  userName = etUserName.text.toString()
                  password = etPassword.text.toString()

                  if (chekValidation(userName!!, password!!) && checkInternet()){
                  val requestLogin = RetrofitInstance.getRetrofitInstance().create(ServiceAPIInterface::class.java)
                  GlobalScope.launch {
                     val userLogin = UserLogin(1, userName!!,password!!)
                      val loginResponse = requestLogin.loginUser(userLogin)
                      if (loginResponse.isSuccessful && loginResponse.code()==200){
                          Log.d(TAG, "response of user: "+loginResponse.body().toString())
                      }

                  }
                  }else{
                      val toast = Toast.makeText(this,"Please enter the required field!",Toast.LENGTH_LONG)
                      toast.show()
                  }
           }
       }
    }

    private fun checkInternet(): Boolean {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return true
    }

    private fun chekValidation(userName: String, password: String): Boolean {
        if (userName.isEmpty() && password.isEmpty()){
            val toast = Toast.makeText(this,"Please enter the User name and password!",Toast.LENGTH_LONG)
            toast.show()
            return false
        }
        return true
    }


}