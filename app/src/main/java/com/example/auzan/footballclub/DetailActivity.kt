package com.example.auzan.footballclub

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.util.changeFormatDate
import com.example.auzan.footballclub.util.strToDate
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
class DetailActivity: AppCompatActivity() {

    private lateinit var event: EventItem
    private lateinit var dataview: ScrollView
    private lateinit var imgHomeBadge: ImageView
    private lateinit var imgAwayBadge: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        event = intent.getParcelableExtra("EVENT")
        val date = strToDate(event.eventDate)

        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(8)
            dataview = scrollView {

                // date
                textView {
                    id = R.id.tv_event_date
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    backgroundColor = Color.WHITE
                    orientation = LinearLayout.HORIZONTAL
                    padding = dip(8)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        linearLayout {
                            imgHomeBadge = imageView {
                            }.lparams {
                                width = dip(100)
                                height = dip(100)
                                gravity = Gravity.CENTER
                            }

                            textView {
                                id = R.id.tv_home_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = "home"
                            }.lparams(matchParent, wrapContent, 1f)
                        }.lparams(matchParent, wrapContent, 1f)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                id = R.id.tv_home_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                                text = "0"
                            }

                            textView {
                                text = "vs"
                            }

                            textView {
                                id = R.id.tv_away_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                                text = "0"
                            }
                        }

                        linearLayout {
                            imgAwayBadge = imageView {
                            }.lparams {
                                width = dip(100)
                                height = dip(100)
                                gravity = Gravity.CENTER
                            }

                            textView {
                                id = R.id.tv_away_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = "away"
                            }.lparams(matchParent, wrapContent, 1f)
                        }.lparams(matchParent, wrapContent, 1f)

                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(16), dip(8), dip(16), dip(8))
                }

            }

        }

    }
}