package com.example.mvvmpr.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpr.R
import com.example.mvvmpr.databinding.ActivityPlantBinding

class PlantActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPlantBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllData()

        //viewModel의 result변수 가 값이 변경되면 아래 함수가 실행 됌
        //result변수는 LiveData타입
        viewModel.result.observe(this) { it ->
            val customAdapter = PlantAdapter(this@PlantActivity, it)
            binding.rcv.adapter = customAdapter
        }
    }
}