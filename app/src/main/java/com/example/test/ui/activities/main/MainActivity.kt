package com.example.test.ui.activities.main

import androidx.activity.viewModels
import com.example.test.R
import com.example.test.databinding.MainActivityBinding
import com.example.test.utils.base.BaseActivity

class MainActivity : BaseActivity<MainActivityBinding>() {

    val viewModel: MainViewModel by viewModels()

    override fun getLayout() = R.layout.main_activity

    override fun initDependency() {
    }

    override fun initObservers() {

    }

    override fun initView() {
        initElements()
    }

}