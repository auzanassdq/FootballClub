package com.example.auzan.footballclub.main

import android.R
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.auzan.footballclub.DetailActivity
import com.example.auzan.footballclub.R.array.league
import com.example.auzan.footballclub.R.color.colorAccent
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.model.Team
import com.example.auzan.footballclub.util.invisile
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
class MainActivity : AppCompatActivity(), MainView {

    private var teams: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var emptyDataView: LinearLayout
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView{
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }

                }
            }
        }

        adapter = EventAdapter(teams)

        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getEventList()


        swipeRefresh.onRefresh {
            presenter.getEventList()
        }

    }

    override fun showLoading() {
//        progressBar.visible()
    }

    override fun hideLoading() {
//        progressBar.invisile()
    }

    override fun showTeamList(data: List<EventItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyData() {
//        progressBar.invisile()
//        recyclerView.invisile()
        emptyDataView.visible()
    }

}
