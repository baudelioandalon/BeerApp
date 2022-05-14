package com.example.test.ui.activities.main.data

import com.example.test.domain.entity.BeerResponse
import com.example.test.networking.core.DataResponse

interface GetBeerDataSource {
    suspend fun getBeers(): DataResponse<BeerResponse>
}