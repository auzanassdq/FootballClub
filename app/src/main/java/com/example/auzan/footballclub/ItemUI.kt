package com.example.auzan.footballclub

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

/**
 * Created by auzan on 11/15/2018.
 * Github: @auzanassdq
 */

class ItemUI : AnkoComponent<ViewGroup> {

    companion object {
        const val ImgClub = 1
        const val tvClub = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){

        verticalLayout{
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            imageView {
                id = ImgClub
            }.lparams(dip(50), dip(50))

            textView {
                id = tvClub
            }.lparams(wrapContent, wrapContent){
                margin = dip(10)
            }
        }
    }
}