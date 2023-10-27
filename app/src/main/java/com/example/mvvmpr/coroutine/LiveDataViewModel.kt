package com.fsoon.android.fastcampus.newtech.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel: ViewModel() {

    var value = 0

    private val _currentValue = MutableLiveData<Int>()
    val currentValue : LiveData<Int>
        get() { return _currentValue }

    private val _currentInput = MutableLiveData<String>()
    val currentInput: LiveData<String>
        get() {return _currentInput}

    init {
        _currentValue.value = 0
    }

    fun updateValue(type: ExType, input: Int) {
        when(type) {
            ExType.PLUS -> _currentValue.value = _currentValue.value?.plus(input)
            ExType.MINUS -> _currentValue.value = _currentValue.value?.minus(input)
            else -> {}
        }
        this._currentInput.value = ""
    }

}