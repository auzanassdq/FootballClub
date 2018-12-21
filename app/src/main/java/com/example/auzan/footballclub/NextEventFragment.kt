package com.example.auzan.footballclub

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView

/**
 * Created by auzan on 12/16/2018.
 * Github: @auzanassdq
 */

class NextEventFragment : Fragment(), AnkoComponent<Context> {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            textView{
                text = "Next"
            }
        }
    }

}