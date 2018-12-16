package com.example.auzan.footballclub.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class ApiRepository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}