package com.fsoon.android.fastcampus.newtech.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowViewModel: ViewModel() {

    var value = 0

    /**
     * State Flow
     * 코루틴 스코프를 이용해 값을 전달
     *
     * collect{} 현재 값을 그대로 보낸다
     * 예) 작업이 오래걸려도 모든 값을 보낸다
     *
     * collectLatest{} 마지막 값만 보낸다
     * 예) 작업이 오래걸리면 중간값을 무시하고 마지막 값만 보낸다
     *
     * **/
    private val _currentValue = MutableStateFlow<Int>(0)
    val currentValue : StateFlow<Int>
        get() { return _currentValue }

    private val _currentInput = MutableStateFlow<String>("")
    val currentInput: StateFlow<String>
        get() {return _currentInput}

    /**
     * Shared Flow
     * 지속적으로 값을 보낸다?
     * 보통 예외처리 같은 특수한 상황에 쓴다고 함
     * **/
    private val _inputError = MutableSharedFlow<String>()
    val inputError: SharedFlow<String> = _inputError

    /**
     * Flow
     * 기존의 State Flow, Shared Flow 등의 값을 가공하여 보내는 방법
     * **/
    val currentFlowInfo : Flow<String> = _currentValue.map { "현재 값: ${it}"}

    init {
        _currentValue.value = 0
    }

    fun updateValue(type: ExType, input: Int) {
        viewModelScope.launch {

            //Shared Flow
            if (input < 1) {
                _inputError.emit("0 보단 커야 해요")
                return@launch
            }

            when(type) {
                ExType.PLUS -> _currentValue.emit(_currentValue.value.plus(input))
                ExType.MINUS -> _currentValue.emit(_currentValue.value.minus(input))
            }
        }
        this._currentInput.value = ""
    }

}