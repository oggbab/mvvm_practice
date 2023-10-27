package com.example.mvvmpr.mvvm.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    /**
     * LiveData는 Observer한 타입으로 lifeCycler에 맞춰 관찰자에게 데이터를 보내준다
     *
     * 변수가 2개여야하는 이유:
     * 외부에서 접근할때 thread-safe하려면 불변값객체 ImutableLiveData가 필요하고,
     * 내부에서 데이터 가공시에는 값이 변경되어야 하므로 2개가 필요하다.
     * **/
    private val _isVisible = MutableLiveData<Boolean>()
    val isVisible: LiveData<Boolean>
        get() = _isVisible

    init {
        _isVisible.value = false
    }

    fun setVisible() {
        _isVisible.value = true
    }

    fun setInVisible() {
        _isVisible.value = false
    }
}