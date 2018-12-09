package com.example.auzan.footballclub.detail

import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by auzan on 12/1/2018.
 * Github: @auzanassdq
 */

class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getEventDetails(homeTeamId: String, awayTeamId: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {

            val homeTeam = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(homeTeamId)).await(),
                TeamResponse::class.java
            )

            val awayTeam = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(awayTeamId)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeamDetails(homeTeam.teams, awayTeam.teams)

        }
    }
}