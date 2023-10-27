package com.example.mvvmpr.hilt.api.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.mvvmpr.R
import com.example.mvvmpr.databinding.ActivityHiltApiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltApiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHiltApiBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hilt_api)
        viewModel.getData().observe(this) { data ->
            data?.let {
                binding.textView.text = it
            }
        }
    }
}