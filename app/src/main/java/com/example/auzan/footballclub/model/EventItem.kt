package com.example.auzan.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

@Parcelize
class EventItem (

    @SerializedName("idEvent")
    var eventId: String? = null ,

    @SerializedName("strEvent")
    var eventName: String? = null ,

    @SerializedName("dateEvent")
    var eventDate: String? = null ,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null ,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null ,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null ,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null ,

    @SerializedName("intHomeScore")
    var homeScore: String? = null ,

    @SerializedName("intAwayScore")
    var awayScore: String? = null ,

    @SerializedName("intHomeShots")
    var homeShots: String? = null ,

    @SerializedName("intAwayShots")
    var awayShots: String? = null ,

    @SerializedName("strHomeFormation")
    var homeFormation: String? = null ,

    @SerializedName("strAwayFormation")
    var awayFormation: String? = null ,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null ,

    @SerializedName("strAwayGoalDetails")
    var awayGoalsDetails: String? = null ,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeLineupGoalKeeper: String? = null ,

    @SerializedName("strHomeLineupDefense")
    var homeLineupDefense: String? = null ,

    @SerializedName("strHomeLineupMidfield")
    var homeLineupMidfield: String? = null ,

    @SerializedName("strHomeLineupForward")
    var homeLineupForward: String? = null ,

    @SerializedName("strHomeLineupSubstitutes")
    var homeLineupSubstitutes: String? = null ,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayLineupGoalKeeper: String? = null ,

    @SerializedName("strAwayLineupDefense")
    var awayLineupDefense: String? = null ,

    @SerializedName("strAwayLineupMidfield")
    var awayLineupMidfield: String? = null ,

    @SerializedName("strAwayLineupForward")
    var awayLineupForward: String? = null ,

    @SerializedName("strAwayLineupSubstitutes")
    var awayLineupSubstitutes: String? = null ,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null

) : Parcelable {

    companion object {
        const val TABLE_FAVORITES = "TABLE_FAVORITES"
        const val ID = "ID"
        const val ID_EVENT = "ID_EVENT"
        const val DATE = "DATE"

        // home team
        const val HOME_ID = "HOME_ID"
        const val HOME_TEAM = "HOME_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val HOME_FORMATION = "HOME_FORMATION"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_SHOTS = "HOME_SHOTS"
        const val HOME_LINEUP_GOALKEEPER = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTES = "HOME_LINEUP_SUBSTITUTES"

        // away team
        const val AWAY_ID = "AWAY_ID"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val AWAY_FORMATION = "AWAY_FORMATION"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_SHOTS = "AWAY_SHOTS"
        const val AWAY_LINEUP_GOALKEEPER = "AWAY_LINEUP_GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTES = "AWAY_LINEUP_SUBSTITUTES"
    }

}