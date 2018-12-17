package com.example.auzan.footballclub

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.view.*
import kotlinx.android.synthetic.main.abc_action_bar_title_item.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.viewPager

/**
 * Created by auzan on 12/16/2018.
 * Github: @auzanassdq
 */

class MatchFragment : Fragment(), AnkoComponent<Context> {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {

            themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {

                    toolbar {
                        id = R.id.toolbar
                        popupTheme = R.style.ThemeOverlay_AppCompat_Light
                    }.lparams(matchParent, dimenAttr(R.attr.actionBarSize)) {
                        gravity = Gravity.TOP
                        scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
                    }

                    tabLayout {
                        id = R.id.tab_layout
                    }.lparams(matchParent, dimenAttr(R.attr.actionBarSize)) {
                        gravity = Gravity.BOTTOM
                    }



            }.lparams(matchParent)
            viewPager {
                id = R.id.view_pager
            }.lparams(matchParent, matchParent) {
                behavior = Class.forName(resources.getString(R.string.appbar_scrolling_view_behavior)).newInstance() as CoordinatorLayout.Behavior<*>?
            }


        }
    }


}