package com.example.auzan.footballclub.main

import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.model.EventResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.NullPointerException

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
class MainPresenter (
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson) {


    fun getEventList(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventMatchs()),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()

                try {
                    view.showTeamList(data.events!!)
                } catch (e: NullPointerException) {
                    view.showEmptyData()
                }

            }
        }
    }
}