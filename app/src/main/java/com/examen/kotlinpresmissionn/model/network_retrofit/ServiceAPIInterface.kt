package com.examen.kotlinpresmissionn.model.network_retrofit

import com.examen.kotlinpresmissionn.model.login.LoginRespones
import com.examen.kotlinpresmissionn.model.login.UserLogin
import retrofit2.Response

interface ServiceAPIInterface {

    suspend fun loginUser(userLogin: UserLogin):Response<LoginRespones>
}