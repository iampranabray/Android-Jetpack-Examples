package com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CounterInterface {

    val counterData: Flow<Int>
    fun setCounter(): StateFlow<Int>
}

class Counter : CounterInterface {
    private val _counter = MutableStateFlow<Int>(1)
    private var counter: StateFlow<Int> = _counter
    override val counterData: Flow<Int> = counter

    override fun setCounter(): StateFlow<Int> {
        _counter.value = _counter.value + 1
        return counter
    }

}