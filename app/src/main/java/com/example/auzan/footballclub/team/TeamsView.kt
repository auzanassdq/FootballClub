package com.example.auzan.footballclub.team

import com.example.auzan.footballclub.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}