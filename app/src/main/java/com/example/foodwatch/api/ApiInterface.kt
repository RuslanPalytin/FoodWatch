package com.example.foodwatch.api

import com.example.foodwatch.model.OrderModel
import com.example.foodwatch.model.TokenModel
import com.example.foodwatch.model.UserLoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/auth/login")
    fun loginUser(
        @Body loginModel: UserLoginModel
    ): Call<TokenModel>

    @GET("api/delivery/orders/histories")
    fun getHistoryOrder(
        @Header ("Authorization") token: String,
    ): Call<List<OrderModel>>
}