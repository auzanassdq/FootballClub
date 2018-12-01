package com.example.auzan.footballclub

import com.example.auzan.footballclub.model.Team

/**
 * Created by auzan on 12/1/2018.
 * Github: @auzanassdq
 */
interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(homeTeam: List<Team>, awayTeam: List<Team>)
}