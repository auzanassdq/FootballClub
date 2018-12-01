package com.example.auzan.footballclub.util

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisile() {
    visibility = View.INVISIBLE
}

@SuppressLint("SimpleDateFormat")
fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date {
    val format = SimpleDateFormat(pattern)
    val date = format.parse(strDate)

    return date
}

@SuppressLint("SimpleDateFormat")
fun changeFormatDate(date: Date?): String? = with(date ?: Date()){
    SimpleDateFormat("EEE, dd MMM yyy").format(this)
}