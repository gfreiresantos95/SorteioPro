package com.gabrielfreire.sorteiopro.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(value = true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _keepSplashOn = MutableStateFlow(value = true)
    val keepSplashOn: StateFlow<Boolean> = _keepSplashOn.asStateFlow()

    init {
        viewModelScope.launch {
            delay(timeMillis = 1000L)

            _isLoading.value = false
            _keepSplashOn.value = false
        }
    }
}