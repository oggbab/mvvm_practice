package com.example.mvvmpr.mvvm.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmpr.R
import com.example.mvvmpr.databinding.ActivityPlantBinding
import com.example.mvvmpr.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 데이터바인딩
         * 1번 방법
         * **/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view)
        //Activity의 lifecycler을 동기화
        binding.lifecycleOwner = this

        /**
         * 데이터바인딩
         * 1번 방법
         * **/
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * 1번 방법
         * VimodelProvider
         * **/
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        /**
         * 2번 방법
         * kotlin-ktx**/
        val viewModel2 : MainViewModel by viewModels()


        //xml내 선언한 변수에 custom viewmodel을 할당한다
        binding.viewModel = MainViewModel()

        with(binding) {
            edtLoginEmail.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0!!.length > 3){
                        viewModel?.setVisible()
                    }
                    else{
                        viewModel?.setInVisible()
                    }
                }
            })
        }
    }
}