package com.example.auzan.footballclub.main

import com.example.auzan.footballclub.db.Favorite
import com.example.auzan.footballclub.model.EventItem

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
interface MainView {
    fun showEmptyData()
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<EventItem>)
    fun showFavoritetList(data: List<Favorite>)
}