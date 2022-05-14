package com.example.test.networking.core

data class DataResponse<R>(
    val statusRequest: StatusRequestEnum = StatusRequestEnum.NONE,
    val successData: R? = null,
    val errorData: String? = null,
)
