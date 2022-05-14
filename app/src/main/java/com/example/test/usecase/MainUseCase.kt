package com.example.test.usecase

import com.example.test.domain.entity.BeerResponse
import com.example.test.networking.core.DataResponse
import com.example.test.ui.activities.main.domain.MainRepository
import com.example.test.utils.base.In
import com.example.test.utils.base.Out
import com.example.test.utils.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MainUseCase(private val mainRepository: MainRepository) :
    UseCase<MainUseCase.Input, MainUseCase.Output> {

    class Input : In()
    inner class Output(val response: DataResponse<BeerResponse>) : Out()

    override suspend fun execute(input: Input): Flow<Output> {
        return mainRepository.getBeers().flowOn(Dispatchers.IO).map {
            Output(it)
        }
    }

}