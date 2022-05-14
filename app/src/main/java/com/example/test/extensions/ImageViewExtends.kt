package com.example.test.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat

fun ImageView.changeDrawable(drawable: Int) {
    setImageDrawable(
        ContextCompat.getDrawable(
            this.context,
            drawable
        )
    )
}