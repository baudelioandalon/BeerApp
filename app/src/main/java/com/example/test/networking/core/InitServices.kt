package com.example.test.networking.core

import com.example.test.BuildConfig
import com.example.test.networking.core.retrofit.ApiServices
import com.example.test.networking.core.retrofit.RetroClient

class InitServices<R> {

    fun getServiceNoHeaderNoBody(endPoint: String): R {
        return RetroClient.getRestEngine().create(ApiServices::class.java)
            .serviceResponseGetNoHeaderNoBody(
                BuildConfig.BASE_URL + endPoint
            ) as R
    }

}
