package com.example.test.networking.core.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    fun serviceResponseGetNoHeaderNoBody(@Url url: String): Call<Any>
}