package com.example.test.ui.activities.main.data.datasource.remote

import com.example.test.ui.activities.main.BeerDataSource
import com.example.test.ui.activities.main.data.GetBeerDataSource

class ARemoteBeerDataSource : GetBeerDataSource {
    override suspend fun getBeers() = BeerDataSource.getData()
}