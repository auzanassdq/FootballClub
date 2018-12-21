package com.example.auzan.footballclub

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by auzan on 12/18/2018.
 * Github: @auzanassdq
 */
class ViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastEventFragment()
            }
            else -> {
                return NextEventFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            else -> {
                return "Next Match"
            }
        }
    }

}