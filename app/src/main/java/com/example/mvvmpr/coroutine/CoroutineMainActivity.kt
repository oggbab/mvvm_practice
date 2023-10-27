package com.example.mvvmpr.mvvm.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvmpr.databinding.ActivityCoroutineMainBinding
import com.fsoon.android.fastcampus.newtech.coroutine.ExType
import com.fsoon.android.fastcampus.newtech.coroutine.FlowViewModel
import com.fsoon.android.fastcampus.newtech.coroutine.LiveDataViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoroutineMainActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel
    private lateinit var viewModelStateFlow: FlowViewModel
    private lateinit var binding: ActivityCoroutineMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LiveDataViewModel::class.java]
        viewModelStateFlow = ViewModelProvider(this).get(FlowViewModel::class.java)

        binding.plusbutton.setOnClickListener {
//            viewModel.updateValue(ExType.PLUS, binding.numberEditText.text.toString().toInt())
            viewModelStateFlow.updateValue(ExType.PLUS, binding.numberEditText.text.toString().toInt())
        }
        binding.minusbutton.setOnClickListener {
//            viewModel.updateValue(ExType.MINUS, binding.numberEditText.text.toString().toInt())
            viewModelStateFlow.updateValue(ExType.MINUS, binding.numberEditText.text.toString().toInt())
        }

//        typeLiveData()
        typeStateFlow()
    }

    fun typeStateFlow() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModelStateFlow.currentInput.collectLatest {
                        Log.d(TAG, "currentInput 데이터값 변경: ${it}")
                        binding.numberEditText.setText(it)
                    }
                }

                launch {
                    viewModelStateFlow.currentValue.collect {
                        Log.d(TAG, "currentValue 데이터값 변경: ${it}")
                        binding.numberTextView.text = it.toString()
                    }
                }

                //Flow
                launch {
                    viewModelStateFlow.currentFlowInfo.collect {
                        Log.d(TAG, "currentValue 데이터값 가공: ${it}")
                        binding.numberTextView.text = it.toString()
                    }
                }

                //SharedFlow
                launch {
                    viewModelStateFlow.inputError.collect {
                        Log.d(TAG, "inputError 에러: $it")
                        Toast.makeText(this@CoroutineMainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }


    fun typeLiveData() {
        viewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "currentValue 데이터값 변경: ${it}")
            binding.numberTextView.text = it.toString()
        })

        viewModel.currentInput.observe(this, Observer {
            Log.d(TAG, "currentInput 데이터값 변경: ${it}")
            binding.numberEditText.setText(it)
        })

    }

    companion object {
        const val TAG = "soon2"
    }
}