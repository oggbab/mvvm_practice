package com.example.mvvmpr.hilt.api.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.mvvmpr.hilt.api.conn.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    var liveData = MutableLiveData<String>("응답 없음")

    fun getData() = liveData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val data = repository.checkSMS("param1", "param2")
            when (data.isSuccessful) {
                true -> {
                    liveData.postValue(data.body().toString())
                }
                else -> {
                    Log.e("tag","TEST -> ${data.body()}")
                }
            }
        }
    }
}