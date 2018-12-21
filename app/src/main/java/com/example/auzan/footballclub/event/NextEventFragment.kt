package com.example.auzan.footballclub.event

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.auzan.footballclub.R
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.detail.DetailActivity
import com.example.auzan.footballclub.main.EventAdapter
import com.example.auzan.footballclub.main.EventPresenter
import com.example.auzan.footballclub.main.MainView
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.util.invisible
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by auzan on 12/16/2018.
 * Github: @auzanassdq
 */

class NextEventFragment : Fragment(), AnkoComponent<Context>, MainView {

    private var events: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEnv()
    }

    private fun setupEnv() {
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)

        adapter = EventAdapter(events) {
            context?.startActivity<DetailActivity>("id" to "${it.eventId}")
        }

        presenter.getEventNextList()
        listTeam.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getEventNextList()
        }
    }

    override fun showEmptyData() {
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<EventItem>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(matchParent, wrapContent)

                    listTeam = recyclerView {
                        lparams(matchParent, wrapContent)

                        id = R.id.rv_event
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }

            }

        }
    }


}