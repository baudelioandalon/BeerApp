package com.example.test.utils

import com.example.test.R

private val arrayListColors = arrayListOf(
    R.color.color_one,
    R.color.color_two,
    R.color.color_three,
    R.color.color_four,
    R.color.color_five,
    R.color.color_six,
    R.color.color_seven
)

fun getRandomColor() = arrayListColors[(0..6).random()]