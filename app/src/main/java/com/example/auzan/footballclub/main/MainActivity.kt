package com.example.auzan.footballclub.main

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import com.example.auzan.footballclub.MatchFragment
import com.example.auzan.footballclub.R
import com.example.auzan.footballclub.R.id.btn_nv
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.support.v4.onRefresh

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: EventPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutMainActivity()
        setView()
    }

    private fun setView() {
        setFragment(MatchFragment())
    }

    private fun layoutMainActivity() {
        relativeLayout {

            frameLayout {
                id = R.id.frame_layout
            }.lparams(matchParent, matchParent)

            bottomNavigationView {
                id = btn_nv
                backgroundColor = Color.WHITE

                menu.apply {
                    add(0, R.id.btn_prev_match, 0, "Match")
                        .setIcon(R.drawable.ic_watch)
                        .setOnMenuItemClickListener {
                            setFragment(MatchFragment())
                            false
                        }

                    add(0, R.id.btn_next_match, 0, "Next Match")
                        .setIcon(R.drawable.ic_event)
                        .setOnMenuItemClickListener {
                            presenter.getEventNextList()
                            swipeRefresh.onRefresh {
                                presenter.getEventNextList()
                            }
                            false
                        }
                    add(0, R.id.btn_fav, 0, "Favorite")
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

    private fun setFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }

}