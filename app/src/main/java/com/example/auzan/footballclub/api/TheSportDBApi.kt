package com.example.auzan.footballclub.api

import android.net.Uri
import com.example.auzan.footballclub.BuildConfig

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

object TheSportDBApi {

    fun getTeams(league: String?) : String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
    }

    fun getEventMatchs() : String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }
}