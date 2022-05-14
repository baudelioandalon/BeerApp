package com.example.test.ui.fragments.details

import com.example.test.R
import com.example.test.databinding.DetailFragmentBinding
import com.example.test.ui.activities.main.MainViewModel
import com.example.test.utils.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    val viewModel: MainViewModel by sharedViewModel()

    override fun getLayout() = R.layout.detail_fragment

    override fun initView() {
        initElements()
    }

}