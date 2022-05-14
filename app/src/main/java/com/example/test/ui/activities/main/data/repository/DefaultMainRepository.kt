package com.example.test.ui.activities.main.data.repository

import com.example.test.domain.entity.BeerResponse
import com.example.test.networking.core.DataResponse
import com.example.test.ui.activities.main.data.GetBeerDataSource
import com.example.test.ui.activities.main.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMainRepository(
    private val getBeerDataSource: GetBeerDataSource
) : MainRepository {
    override suspend fun getBeers(): Flow<DataResponse<BeerResponse>> = flow {
        emit(getBeerDataSource.getBeers())
    }
}