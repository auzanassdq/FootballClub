package com.example.auzan.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * Created by auzan on 12/16/2018.
 * Github: @auzanassdq
 */

class MatchFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuTab()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.mn_search -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun menuTab() {
        setHasOptionsMenu(true)

        with(activity as AppCompatActivity) {
//            setSupportActionBar(toolbar)

            view_pager.adapter = ViewPagerAdapter(supportFragmentManager)

            tab_layout.setupWithViewPager(view_pager)
        }


        tab_layout.setupWithViewPager(view_pager)
    }

}