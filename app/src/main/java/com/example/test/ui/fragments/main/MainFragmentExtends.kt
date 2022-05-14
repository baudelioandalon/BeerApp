package com.example.test.ui.fragments.main

import com.example.test.extensions.setOnSingleClickListener
import kotlin.system.exitProcess

fun MainFragment.initElements() {
    binding.apply {
        rvBeers.apply {
            adapter = adapterRecyclerBeers
        }
        btnOut.setOnSingleClickListener {
            exitProcess(1)
        }
        viewModel.executeGetBeers()
    }
}