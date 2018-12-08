package com.example.auzan.footballclub.main

import android.content.Context
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.db.database
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.model.EventResponse
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {


    fun getEventPastList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventPast()),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getEventNextList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventNext()),
                EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }

    fun getFavorite(context: Context) {
        view.showLoading()
        val data: MutableList<EventItem> = mutableListOf()

        doAsync {
            context.database.use {
                val favorite = select(EventItem.TABLE_FAVORITES)
                    .parseList(classParser<EventItem>())

                data.addAll(favorite)
            }

            uiThread {
                view.hideLoading()
                if (data.size > 0) {
                    view.showEventList(data)
                } else {
                    view.showEmptyData()
                }
            }
        }
    }
}