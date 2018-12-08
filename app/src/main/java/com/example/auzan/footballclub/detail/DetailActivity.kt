package com.example.auzan.footballclub.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import com.example.auzan.footballclub.R
import com.example.auzan.footballclub.R.drawable.ic_favorite
import com.example.auzan.footballclub.R.drawable.ic_favorite_border
import com.example.auzan.footballclub.R.id.mn_favorites
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.api.ApiRepository
import com.example.auzan.footballclub.db.database
import com.example.auzan.footballclub.model.Team
import com.example.auzan.footballclub.util.changeFormatDate
import com.example.auzan.footballclub.util.invisible
import com.example.auzan.footballclub.util.strToDate
import com.example.auzan.footballclub.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class DetailActivity: AppCompatActivity(), DetailView {

    private lateinit var eventItem: EventItem
    private lateinit var presenter: DetailPresenter

    private lateinit var progressView: ProgressBar
    private lateinit var dataview: ScrollView

    private lateinit var imgHomeBadge: ImageView
    private lateinit var imgAwayBadge: ImageView

    private var menuFavorites: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventItem = intent.getParcelableExtra("Event")

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        layoutDetailActivity(eventItem)
        getTeam(eventItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        menuFavorites = menu
        setFavorite()

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            mn_favorites -> {
                if (isFavorite) removeFavorites() else addFavorites()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuFavorites?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite)
        else
            menuFavorites?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite_border)
    }

    private fun addFavorites() {
        try {
            database.use {
                insert(EventItem.TABLE_FAVORITES,
                    EventItem.ID_EVENT to eventItem.eventId,
                    EventItem.DATE to eventItem.eventDate,

                    // home team
                    EventItem.HOME_ID to eventItem.homeTeamId,
                    EventItem.HOME_TEAM to eventItem.homeTeam,
                    EventItem.HOME_SCORE to eventItem.homeScore,
                    EventItem.HOME_FORMATION to eventItem.homeFormation,
                    EventItem.HOME_GOAL_DETAILS to eventItem.homeGoalDetails,
                    EventItem.HOME_SHOTS to eventItem.homeShots,
                    EventItem.HOME_LINEUP_GOALKEEPER to eventItem.homeLineupGoalKeeper,
                    EventItem.HOME_LINEUP_DEFENSE to eventItem.homeLineupDefense,
                    EventItem.HOME_LINEUP_MIDFIELD to eventItem.homeLineupMidfield,
                    EventItem.HOME_LINEUP_FORWARD to eventItem.homeLineupForward,
                    EventItem.HOME_LINEUP_SUBSTITUTES to eventItem.homeLineupSubstitutes,

                    // away team
                    EventItem.AWAY_ID to eventItem.awayTeamId,
                    EventItem.AWAY_TEAM to eventItem.awayTeam,
                    EventItem.AWAY_SCORE to eventItem.awayScore,
                    EventItem.AWAY_FORMATION to eventItem.awayFormation,
                    EventItem.AWAY_GOAL_DETAILS to eventItem.awayGoalsDetails,
                    EventItem.AWAY_SHOTS to eventItem.awayShots,
                    EventItem.AWAY_LINEUP_GOALKEEPER to eventItem.awayLineupGoalKeeper,
                    EventItem.AWAY_LINEUP_DEFENSE to eventItem.awayLineupDefense,
                    EventItem.AWAY_LINEUP_MIDFIELD to eventItem.awayLineupMidfield,
                    EventItem.AWAY_LINEUP_FORWARD to eventItem.awayLineupForward,
                    EventItem.AWAY_LINEUP_SUBSTITUTES to eventItem.awayLineupSubstitutes)
            }
//            snackbar("Add to favorite").show()
            toast("Add to favorite").show()
        } catch (e: SQLiteConstraintException) {
            toast("Error: ${e.message}").show()
        }
    }

    private fun removeFavorites() {
        try {
            database.use {
                delete(EventItem.TABLE_FAVORITES,
                    EventItem.ID_EVENT + " = {id}",
                    "id" to eventItem.eventId.toString())
            }
            toast("Delete from favorite")
        } catch (e: SQLiteConstraintException) {
            toast("Error: ${e.message}")
        }
    }

    private fun isFavorite(): Boolean {
        var bFavorite = false

        database.use {
            val favorites = select(EventItem.TABLE_FAVORITES)
                .whereArgs(EventItem.ID_EVENT + " = {id}",
                    "id" to eventItem.eventId.toString())
                .parseList(classParser<EventItem>())

            bFavorite = !favorites.isEmpty()
        }

        return bFavorite
    }

    override fun showLoading() {
        progressView.visible()
        dataview.invisible()
    }

    override fun hideLoading() {
        progressView.invisible()
        dataview.visible()
    }

    override fun showTeamDetails(homeTeam: List<Team>, awayTeam: List<Team>) {
        Picasso.get().load(homeTeam[0].teamBadge).into(imgHomeBadge)
        Picasso.get().load(awayTeam[0].teamBadge).into(imgAwayBadge)
    }

    private fun getTeam(event: EventItem){
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getEventDetails(event.homeTeamId!!, event.awayTeamId!!)

        isFavorite = isFavorite()
    }

    @SuppressLint("RtlHardcoded")
    private fun layoutDetailActivity(event: EventItem){
        val date = strToDate(event.eventDate)
        relativeLayout {
            lparams(matchParent, wrapContent)

            dataview = scrollView {

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    backgroundColor = Color.WHITE
                    padding = dip(16)

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
                                textColor = ContextCompat.getColor(this@DetailActivity,
                                    R.color.colorPrimary
                                )
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
                                text = context.getString(R.string.VS)
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
                                textColor = ContextCompat.getColor(this@DetailActivity,
                                    R.color.colorPrimary
                                )
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.goals)
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.shots)
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
                        text = context.getString(R.string.Lineups)
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.goal_keeper)

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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.defense)
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.midfield)
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.forward)
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
                            textColor = ContextCompat.getColor(this@DetailActivity,
                                R.color.colorPrimary
                            )
                            text = context.getString(R.string.substitutes)
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
                centerInParent()
            }

        }
    }
}