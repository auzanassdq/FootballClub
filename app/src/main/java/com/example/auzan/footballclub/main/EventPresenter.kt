package com.example.auzan.footballclub.main

import android.content.Context
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.db.database
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.model.EventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class EventPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getEventPastList() {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventPast()).await(),
                EventResponse::class.java
            )

            view.hideLoading()
            view.showEventList(data.events)
        }
    }

    fun getEventNextList() {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventNext()).await(),
                EventResponse::class.java
            )

            view.hideLoading()
            view.showEventList(data.events)
        }
    }

    fun getFavorite(context: Context) {
        view.showLoading()
        val data: MutableList<EventItem> = mutableListOf()

        GlobalScope.launch(Dispatchers.Main) {
            context.database.use {
                val favorite = select(EventItem.TABLE_FAVORITES)
                    .parseList(classParser<EventItem>())

                data.addAll(favorite)
            }

            view.hideLoading()
            if (data.size > 0) {
                view.showEventList(data)
            } else {
                view.showEmptyData()
            }
        }
    }
}