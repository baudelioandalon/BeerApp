package com.example.test.extensions

import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.disableBackButton() {
    onBackPressedDispatcher.addCallback(this) {}
}