package com.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var viewModel : TodoViewModel = ViewModelProviders.of(this)[TodoViewModel::class.java]
        bind.viewModel = viewModel
        bind.lifecycleOwner = this
/*
        viewModel.getAll()?.observe(this, Observer {
            bind.resultTxt.text = it.toString()
        })

        btn_add.setOnClickListener {
            viewModel.insert(edit_txt.text.toString())
        }
*/
    }


}