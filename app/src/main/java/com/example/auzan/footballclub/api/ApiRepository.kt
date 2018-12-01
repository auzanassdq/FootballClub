package com.example.auzan.footballclub.api

import java.net.URL

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class ApiRepository {
    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}