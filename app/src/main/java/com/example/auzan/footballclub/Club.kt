package com.example.auzan.footballclub

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by auzan on 11/15/2018.
 * Github: @auzanassdq
 */

@Parcelize
data class Club (val name: String?, val image: Int?, val desc: String?) : Parcelable