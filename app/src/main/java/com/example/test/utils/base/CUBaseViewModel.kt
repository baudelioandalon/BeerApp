package com.example.test.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun executeFlow(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            block.invoke()
        } catch (e: Exception) {

        }
    }
}