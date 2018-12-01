package com.example.auzan.footballclub

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.EventLog
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import com.example.auzan.footballclub.api.TheSportDBApi
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.R.id.*
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.model.Team
import com.example.auzan.footballclub.util.changeFormatDate
import com.example.auzan.footballclub.util.invisile
import com.example.auzan.footballclub.util.strToDate
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import java.util.*

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */
class DetailActivity: AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter

    private lateinit var progressView: ProgressBar
    private lateinit var dataview: ScrollView

    private lateinit var imgHomeBadge: ImageView
    private lateinit var imgAwayBadge: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var event = intent.getParcelableExtra<EventItem>("Event")

        var TES = "this"
        Log.d(TES, "isinya " + event.eventName)

        layoutDetailActivity(event)
        getTeam(event)

    }

    override fun showLoading() {
        progressView.visible()
        dataview.invisile()
    }

    override fun hideLoading() {
        progressView.invisile()
        dataview.visible()
    }

    override fun showTeamDetails(homeTeam: List<Team>, awayTeam: List<Team>) {
        var TES = "tes"
        Log.d(TES, "ini gambar " + homeTeam[0].teamBadge)
        Log.d(TES, "ini gambar " + awayTeam[0].teamBadge)

        Picasso.get().load(homeTeam[0].teamBadge).into(imgHomeBadge)
        Picasso.get().load(awayTeam[0].teamBadge).into(imgAwayBadge)
    }

    fun getTeam(event: EventItem){
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getEventDetails(event.eventId!!, event.homeTeamId!!, event.awayTeamId!!)
    }

    fun layoutDetailActivity(event: EventItem){
        val date = strToDate(event.eventDate)
        relativeLayout {
            backgroundColor = Color.WHITE
            dataview = scrollView {

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    // Date
                    textView {
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                        text = changeFormatDate(date)
                    }

                    constraintLayout {

                        // Home Team
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            imgHomeBadge = imageView {
                            }.lparams {
                                width = dip(100)
                                height = dip(100)
                                gravity = Gravity.CENTER
                            }

                            textView {
                                text = event.homeTeam
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                textSize = 24f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(wrapContent, wrapContent){
                            leftToLeft = PARENT_ID
                            topToTop = PARENT_ID
                            endToStart = ly_score
                        }

                        // Score
                        linearLayout {
                            id = ly_score
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            padding = dip(16)

                            textView {
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                                text = event.homeScore
                            }.lparams(width = wrapContent, height = wrapContent)

                            textView{
                                padding = dip(16)
                                textSize = 24f
                                text = "VS"
                            }.lparams(width = wrapContent, height = matchParent)

                            textView {
                                text = event.awayScore
                                textSize = 48f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(width = wrapContent, height = wrapContent){
                            horizontalBias = 0.501f
                            leftToLeft = PARENT_ID
                            rightToRight = PARENT_ID
                        }

                        // Away Team
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            imgAwayBadge = imageView {
                                id = img_away_badge
                            }.lparams {
                                gravity = Gravity.CENTER
                                width = dip(100)
                                height = dip(100)
                            }
                            textView {
                                text = event.awayTeam
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                textSize = 24f
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(width = wrapContent, height = wrapContent)
                        }.lparams(width = wrapContent, height = wrapContent){
                            rightToRight = PARENT_ID
                            startToEnd = ly_score
                            topToTop = PARENT_ID
                        }
                    }.lparams(width = matchParent, height = wrapContent)

                }.lparams(width = matchParent, height = matchParent)

            }

            progressView = progressBar {
                indeterminateDrawable.setColorFilter(
                    ContextCompat.getColor(ctx, R.color.colorPrimary),
                    PorterDuff.Mode.SRC_IN
                )
            }.lparams {
                centerInParent()
            }

        }
    }
}

// Backup
//linearLayout {
//    orientation = LinearLayout.VERTICAL
//    padding = dip(16)
//
//    // Date
//    textView {
//        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
//        gravity = Gravity.CENTER
//        text = changeFormatDate(date)
//    }
//
//    linearLayout {
//        gravity = Gravity.CENTER_VERTICAL
//
//        // Home
//        linearLayout {
//            orientation = LinearLayout.VERTICAL
//
//            imgHomeBadge = imageView {
//                event.teamBadge?.let {
//                    Picasso.get()
//                        .load(it)
//                        .fit()
//                        .into(this)
//                }
//            }.lparams {
//                width = dip(100)
//                height = dip(100)
//                gravity = Gravity.CENTER
//            }
//
//            textView {
//                id = R.id.tv_home_team
//                gravity = Gravity.CENTER
//                textSize = 18f
//                text = event.homeTeam
//                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
//            }
//        }.lparams(matchParent, wrapContent, 1f)
//
//        // Score
//        linearLayout {
//            gravity = Gravity.CENTER_VERTICAL
//
//            textView {
//                id = R.id.tv_home_score
//                padding = dip(8)
//                textSize = 20f
//                setTypeface(null, Typeface.BOLD)
//                text = event.homeScore
//            }
//
//            textView {
//                text = "vs"
//            }
//
//            textView {
//                id = R.id.tv_away_score
//                padding = dip(8)
//                textSize = 20f
//                setTypeface(null, Typeface.BOLD)
//                text = event.awayScore
//            }
//        }
//
//        // Away
//        linearLayout {
//            orientation = LinearLayout.VERTICAL
//
//            imgAwayBadge = imageView {
//                event.teamBadge?.let {
//                    Picasso.get()
//                        .load(it)
//                        .fit()
//                        .into(this)
//                }
//            }.lparams {
//                width = dip(100)
//                height = dip(100)
//                gravity = Gravity.CENTER
//            }
//
//            textView {
//                id = R.id.tv_away_team
//                gravity = Gravity.CENTER
//                textSize = 18f
//                text = event.awayTeam
//                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
//            }
//        }.lparams(matchParent, wrapContent, 1f)
//
//    }.lparams(matchParent, matchParent) {
//        setMargins(dip(16), dip(8), dip(16), dip(8))
//    }
//
//}


