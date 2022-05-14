package com.example.test.extensions

import android.view.View
import com.example.test.utils.OnSingleClickListener

fun View.setOnSingleClickListener(doOnClick: ((View) -> Unit)) =
    setOnClickListener(OnSingleClickListener(doOnClick))