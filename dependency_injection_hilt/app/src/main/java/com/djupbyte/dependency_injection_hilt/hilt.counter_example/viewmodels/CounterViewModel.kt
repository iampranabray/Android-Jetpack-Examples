package com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels

import androidx.lifecycle.ViewModel
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface.CounterInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private var counterStatus: CounterInterface
) : ViewModel() {

    val settingsUiState: Flow<Int> = counterStatus.counterData;
    fun doSomething() {
        counterStatus.setCounter()
    }

}




