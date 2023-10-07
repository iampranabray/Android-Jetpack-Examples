package com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels

import androidx.lifecycle.ViewModel
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface.CounterInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private var themeStatus: CounterInterface
) : ViewModel() {

    val settingsUiState: StateFlow<Int> = themeStatus.setCounter();
    fun doSomething() {
        themeStatus.setCounter()
    }

}




