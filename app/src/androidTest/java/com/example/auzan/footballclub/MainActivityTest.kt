package com.example.auzan.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.auzan.footballclub.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by auzan on 12/14/2018.
 * Github: @auzanassdq
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBehavior() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_event)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_event)).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_event)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        Thread.sleep(1000)
        onView(withText("VS")).check(matches(isDisplayed()))
        onView(withText("VS")).perform(click())

        onView(withId(R.id.mn_favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.mn_favorites)).perform(click())
        pressBack()

        onView(withId(R.id.btn_nv)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_fav)).perform(click())

//        Aplikasi terbuka dan menampilkan beberapa view.
//        Menuggu selama 3 detik
//        Memastikan bahwa terdapat sebuah recycler view dengan id rv_event yang ditampilkan.
//        melakukan scroll pada RecyclerView sampai dengan posisi ke-10
//        Tunggu selama 1 detik
//        Memastikan bahwa suatu teks telah ditampilkan dan memberikan tindakan klik pada teks tersebut.
//        Memastikan tombol Favorite telah ditampilkan dan Memberi tindakan klik pada tombol Favorite.
//        Menekan tombol kembali.
//        Memastikan sebuah BottomNavigationView telah ditampilkan.
//        Memberikan tindakan klik pada sebuah menu di BottomNavigationView.

    }


}