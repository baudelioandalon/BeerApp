package com.example.test.ui.activities.main.domain

import com.example.test.domain.entity.BeerResponse
import com.example.test.networking.core.DataResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getBeers(): Flow<DataResponse<BeerResponse>>
}