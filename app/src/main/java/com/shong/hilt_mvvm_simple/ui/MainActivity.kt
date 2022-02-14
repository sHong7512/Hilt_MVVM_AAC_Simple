package com.shong.hilt_mvvm_simple.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.shong.hilt_mvvm_simple.R
import com.shong.hilt_mvvm_simple.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            example = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
            lifecycleOwner = this@MainActivity
        }
    }
}