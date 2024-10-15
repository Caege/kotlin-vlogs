package com.example.viewmodeltest.ui

import androidx.lifecycle.ViewModel
import com.example.viewmodeltest.data.CounterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel(){
private val _uiState : MutableStateFlow<CounterUiState> = MutableStateFlow(CounterUiState())
	val uiState : StateFlow<CounterUiState> = _uiState.asStateFlow()

fun increment () {
	val currentValue = _uiState.value


	_uiState.value = currentValue.copy(value = currentValue.value + 1)
}


	fun reset () {
		_uiState.value = _uiState.value.copy(value = 0)
	}
}