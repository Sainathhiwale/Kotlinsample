package com.examen.kotlinpresmissionn.model.network_retrofit

import com.examen.kotlinpresmissionn.model.login.LoginRespones
import com.examen.kotlinpresmissionn.model.login.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceAPIInterface {
    @POST("api/v1/Users")
    suspend fun loginUser(@Body userLogin: UserLogin):Response<LoginRespones>
}