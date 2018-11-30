package com.example.auzan.footballclub.main

import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.model.Team

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
interface MainView {
    fun showEmptyData()
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventItem>)
}