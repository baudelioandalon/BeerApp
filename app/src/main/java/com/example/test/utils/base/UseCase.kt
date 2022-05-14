package com.example.test.utils.base

import kotlinx.coroutines.flow.Flow

interface UseCase<in I : In, out O : Out> {
    suspend fun execute(input: I): Flow<O>
}

sealed interface Port
open class In : Port
open class Out : Port
object EmptyIn : In()
object EmptyOut : Out()
