package com.example.auzan.footballclub

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.model.Team
import com.example.auzan.footballclub.util.changeFormatDate
import com.example.auzan.footballclub.util.invisile
import com.example.auzan.footballclub.util.strToDate
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

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
        Picasso.get().load(homeTeam[0].teamBadge).into(imgHomeBadge)
        Picasso.get().load(awayTeam[0].teamBadge).into(imgAwayBadge)
    }

    fun getTeam(event: EventItem){
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getEventDetails(event.homeTeamId!!, event.awayTeamId!!)
    }

    fun layoutDetailActivity(event: EventItem){
        val date = strToDate(event.eventDate)
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)
            dataview = scrollView {

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    backgroundColor = Color.WHITE

                    // Date
                    textView {
                        gravity = Gravity.CENTER
                        text = changeFormatDate(date)
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    }.lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER

                        // Home Team
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            imgHomeBadge = imageView {
                            }.lparams {
                                width = dip(80)
                                height = dip(80)
                                gravity = Gravity.CENTER
                            }

                            textView {
                                text = event.homeTeam
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                            }
                        }.lparams(matchParent, wrapContent, 1f)

                        // Score
                        linearLayout {
                            gravity = Gravity.CENTER

                            textView {
                                textSize = 42f
                                setTypeface(null, Typeface.BOLD)
                                text = event.homeScore
                            }

                            textView{
                                padding = dip(16)
                                textSize = 24f
                                text = "VS"
                            }

                            textView {
                                text = event.awayScore
                                textSize = 42f
                                setTypeface(null, Typeface.BOLD)
                            }

                        }.lparams(matchParent, wrapContent, 1f)

                        // Away Team
                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            imgAwayBadge = imageView {
                            }.lparams {
                                gravity = Gravity.CENTER
                                width = dip(80)
                                height = dip(80)
                            }
                            textView {
                                text = event.awayTeam
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                            }
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    view {
                        backgroundColor = Color.LTGRAY
                    }.lparams(matchParent, dip(1)) {
                        topMargin = dip(8)
                    }

                    // goals
                    linearLayout {
                        topPadding = dip(8)

                        textView {
                            text = event.homeGoalDetails
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Goals"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayGoalsDetails
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    // shots
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeShots
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Shots"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayShots
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    view {
                        backgroundColor = Color.LTGRAY
                    }.lparams(matchParent, dip(1)) {
                        topMargin = dip(8)
                    }

                    // lineups
                    textView {
                        topPadding = dip(8)
                        gravity = Gravity.CENTER
                        textSize = 18f
                        setTypeface(null, Typeface.BOLD)
                        text = "Lineups"
                    }

                    // goal keeper
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeLineupGoalKeeper
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Goal Keeper"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayLineupGoalKeeper
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    // defense
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeLineupDefense
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Defense"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayLineupDefense
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    // midfield
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeLineupMidfield
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Midfield"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayLineupMidfield
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    // forward
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeLineupForward
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Forward"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayLineupForward
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                    // substitutes
                    linearLayout {
                        topPadding = dip(16)

                        textView {
                            text = event.homeLineupSubstitutes
                        }.lparams(matchParent, wrapContent, 1f)

                        textView {
                            leftPadding = dip(8)
                            rightPadding = dip(8)
                            textColor = ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary)
                            text = "Substitutes"
                        }

                        textView {
                            gravity = Gravity.RIGHT
                            text = event.awayLineupSubstitutes
                        }.lparams(matchParent, wrapContent, 1f)
                    }

                }

            }

            progressView = progressBar {
                indeterminateDrawable.setColorFilter(
                    ContextCompat.getColor(this@DetailActivity, R.color.colorPrimary),
                    PorterDuff.Mode.SRC_IN
                )
            }.lparams {
                gravity = Gravity.CENTER
            }

        }
    }
}


