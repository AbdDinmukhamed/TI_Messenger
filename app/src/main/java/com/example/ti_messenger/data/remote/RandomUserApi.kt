package com.example.ti_messenger.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") amount: Int = 5): RandomUserResponse
}