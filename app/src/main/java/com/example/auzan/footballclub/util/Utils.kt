package com.example.auzan.footballclub.util

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

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun strToDate(strDate: String?, pattern: String = "yyyy-MM-dd"): Date {
    val format = SimpleDateFormat(pattern, Locale.getDefault())

    return format.parse(strDate)
}

fun changeFormatDate(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyy", Locale.getDefault()).format(this)
}