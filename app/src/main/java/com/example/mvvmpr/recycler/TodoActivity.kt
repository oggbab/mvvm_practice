package com.example.mvvmpr.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.mvvmpr.R
import com.example.mvvmpr.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    private val recyclerViewModel : RecyclerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo)
        binding.viewModel = recyclerViewModel
        binding.lifecycleOwner = this@TodoActivity
    }
}