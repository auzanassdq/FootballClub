package com.example.auzan.footballclub.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

/**
 * Created by auzan on 12/10/2018.
 * Github: @auzanassdq
 */
class UtilsKtTest {

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyy")
        val date = dateFormat.parse("Wed, 28 Feb 2018")
        assertEquals("Wed, 28 Feb 2018", changeFormatDate(date))
    }
}