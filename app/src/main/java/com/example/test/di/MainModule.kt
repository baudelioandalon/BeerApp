package com.example.test.di

import com.example.test.ui.activities.main.MainViewModel
import com.example.test.ui.activities.main.data.GetBeerDataSource
import com.example.test.ui.activities.main.data.datasource.remote.ARemoteBeerDataSource
import com.example.test.ui.activities.main.data.repository.DefaultMainRepository
import com.example.test.ui.activities.main.domain.MainRepository
import com.example.test.usecase.MainUseCase
import com.example.test.utils.base.UseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainModule = module {

    single<GetBeerDataSource>(named("ARemoteBeerDataSource")) {
        ARemoteBeerDataSource()
    }
    single<MainRepository>(named("DefaultMainRepository")) {
        DefaultMainRepository(get(named("ARemoteBeerDataSource")))
    }
    single<UseCase<MainUseCase.Input, MainUseCase.Output>>(named("MainUseCase")) {
        MainUseCase(get(named("DefaultMainRepository")))
    }

    viewModel {
        MainViewModel(
            get(qualifier = named("MainUseCase"))
        )
    }
}