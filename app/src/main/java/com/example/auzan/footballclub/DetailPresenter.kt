package com.example.auzan.footballclub

import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.model.EventResponse
import com.example.auzan.footballclub.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by auzan on 12/1/2018.
 * Github: @auzanassdq
 */
class DetailPresenter (private val view: DetailView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson){

        fun getEventDetails(homeTeamId: String, awayTeamId: String){
            view.showLoading()
            doAsync {

                val homeTeam = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(homeTeamId)),
                    TeamResponse::class.java
                )

                val awayTeam = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(awayTeamId)),
                    TeamResponse::class.java
                )

                uiThread {
                    view.hideLoading()
                    view.showTeamDetails(homeTeam.teams!!, awayTeam.teams!!)
                }
            }
        }
}