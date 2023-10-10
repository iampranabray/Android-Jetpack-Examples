package com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface.CounterInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private var counterStatus: CounterInterface
) : ViewModel() {

    val settingsUiState: StateFlow<DataCountUIState> = counterStatus.counterData.map { data ->
        DataCountUIState.Success(
            countData = DataCount(count = data)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = DataCountUIState.Loading

    )

    fun doSomething() {
        counterStatus.setCounter()
    }

}

data class DataCount(
    val count: Int
)

sealed interface DataCountUIState {
    object Loading : DataCountUIState
    data class Success(val countData: DataCount) : DataCountUIState
}




