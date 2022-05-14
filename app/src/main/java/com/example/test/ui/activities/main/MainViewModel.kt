package com.example.test.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.domain.entity.BeerResponse
import com.example.test.domain.entity.BeerResponseItem
import com.example.test.networking.core.DataResponse
import com.example.test.networking.core.StatusRequestEnum
import com.example.test.usecase.MainUseCase
import com.example.test.utils.base.BaseViewModel
import com.example.test.utils.base.UseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class MainViewModel(private val getUseCase: UseCase<MainUseCase.Input, MainUseCase.Output>) :
    BaseViewModel() {

    val beerList: LiveData<DataResponse<BeerResponse>>
        get() = _beerList
    private val _beerList = MutableLiveData<DataResponse<BeerResponse>>()

    var beerResponseItem: BeerResponseItem? = null

    fun executeGetBeers() {
        executeFlow {
            _beerList.value = DataResponse(statusRequest = StatusRequestEnum.LOADING)
            getUseCase.execute(MainUseCase.Input()).catch {
                _beerList.value = DataResponse(
                    statusRequest = StatusRequestEnum.FAILURE,
                    null, it.message ?: "Algo salio mal"
                )
            }.collect {
                _beerList.postValue(it.response)
            }
        }

    }
}