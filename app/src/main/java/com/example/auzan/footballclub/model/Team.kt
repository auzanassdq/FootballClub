package com.example.auzan.footballclub.model

import com.google.gson.annotations.SerializedName

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)
