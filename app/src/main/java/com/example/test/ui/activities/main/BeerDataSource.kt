package com.example.test.ui.activities.main

import com.example.test.domain.entity.BeerResponse
import com.example.test.networking.core.DataResponse
import com.example.test.networking.core.InitServices
import com.example.test.networking.core.RemoteUrls
import com.example.test.networking.core.ValidResponse
import retrofit2.Call

class BeerDataSource {

    companion object {
        fun getData(): DataResponse<BeerResponse> {
            return ValidResponse<BeerResponse>(BeerResponse::class).validationMethod(
                InitServices<Call<BeerResponse>>().getServiceNoHeaderNoBody(RemoteUrls.BEERS.url)
            )
        }
    }

}
