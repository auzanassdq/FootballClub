package com.example.auzan.footballclub.main

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.*
import com.example.auzan.footballclub.detail.DetailActivity
import com.example.auzan.footballclub.R
import com.example.auzan.footballclub.R.id.btn_nv
import com.example.auzan.footballclub.R.color.colorAccent
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.util.invisible
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class MainActivity : AppCompatActivity(), MainView {

    private var match: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutMainActivity()
        setData()
    }

    private fun setData(){
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        adapter = MainAdapter(match)  {
                items: EventItem -> itemClicked(items)
        }

        presenter.getEventPastList()
        listTeam.adapter = adapter
    }

    private fun itemClicked(event: EventItem) {
        startActivity<DetailActivity>("Event" to event)
    }

    private fun layoutMainActivity(){
        linearLayout{
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{

                    listTeam = recyclerView{
                        layoutManager = LinearLayoutManager(context)
                    }.lparams(matchParent, matchParent) {
                        topOf(btn_nv)
                    }

                    bottomNavigationView {
                        id = btn_nv
                        backgroundColor = Color.WHITE

                        menu.apply {
                            add ("Prev Match")
                                .setIcon(R.drawable.ic_watch)
                                .setOnMenuItemClickListener {
                                    presenter.getEventPastList()
                                    swipeRefresh.onRefresh {
                                        presenter.getEventPastList()
                                    }
                                    false
                                }

                            add("Next Match")
                                .setIcon(R.drawable.ic_event)
                                .setOnMenuItemClickListener {
                                    presenter.getEventNextList()
                                    swipeRefresh.onRefresh {
                                        presenter.getEventNextList()
                                    }
                                    false
                                }
                            add("Favorite")
                                .setIcon(R.drawable.ic_favorite)
                                .setOnMenuItemClickListener {
                                    presenter.getFavorite(this@MainActivity)
                                    swipeRefresh.onRefresh {
                                        presenter.getFavorite(this@MainActivity)
                                    }
                                    false
                                }
                        }
                    }.lparams(matchParent, wrapContent) {
                        alignParentBottom()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        listTeam.invisible()
    }

    override fun hideLoading() {
        listTeam.visible()
    }

    override fun showEmptyData() {
        listTeam.invisible()
    }

    override fun showEventList(data: List<EventItem>) {
        swipeRefresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

}