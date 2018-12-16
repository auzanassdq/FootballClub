package com.example.auzan.footballclub.main

import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.model.EventResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by auzan on 12/14/2018.
 * Github: @auzanassdq
 */
class MainPresenterTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson)
    }

    @Test
    fun getEventPastList() {
        val event: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(event)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getEventPast()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventPastList()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(event)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getEventNext() {
        val event: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(event)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getEventNext()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventNextList()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(event)
            Mockito.verify(view).hideLoading()
        }
    }
}