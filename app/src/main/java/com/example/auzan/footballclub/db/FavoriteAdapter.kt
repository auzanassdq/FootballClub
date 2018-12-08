package com.example.auzan.footballclub.db

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.auzan.footballclub.R
import com.example.auzan.footballclub.R.id.*
import com.example.auzan.footballclub.model.EventItem
import com.example.auzan.footballclub.util.changeFormatDate
import com.example.auzan.footballclub.util.strToDate
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by auzan on 11/30/2018.
 * Github: @auzanassdq
 */

class FavoriteAdapter (private val favItems: List<Favorite>,
                   private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder (
            EventUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = favItems.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bindItem(favItems[position], listener)
    }
}

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView{
                lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    margin = dip(4)
                    radius = 4f
                }

                linearLayout {
                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL

                    linearLayout {
                        backgroundColor = Color.WHITE
                        orientation = LinearLayout.VERTICAL
                        padding = dip(8)

                        // date
                        textView {
                            id = tv_event_date
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            // Home Team
                            textView {
                                id = tv_home_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = context.getString(R.string.home)
                            }.lparams(matchParent, wrapContent, 1f)

                            linearLayout {
                                gravity = Gravity.CENTER_VERTICAL

                                // Home Score
                                textView {
                                    id = tv_home_score
                                    padding = dip(8)
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                    text = "0"
                                }

                                textView {
                                    text = context.getString(R.string.vs)
                                }

                                // Away Score
                                textView {
                                    id = tv_away_score
                                    padding = dip(8)
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                    text = "0"
                                }
                            }

                            // Away Team
                            textView {
                                id = tv_away_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = context.getString(R.string.away)
                            }.lparams(matchParent, wrapContent, 1f)
                        }
                    }.lparams(matchParent, matchParent) {
                        setMargins(dip(16), dip(8), dip(16), dip(8))
                    }
                }

            }
        }
    }

}

class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventDate: TextView = view.find(tv_event_date)
    private val homeTeam: TextView = view.find(tv_home_team)
    private val homeScore: TextView = view.find(tv_home_score)
    private val awayScore: TextView = view.find(tv_away_score)
    private val awayTeam: TextView = view.find(tv_away_team)

    fun bindItem(favItem: Favorite, listener: (Favorite) -> Unit) {
        val date = strToDate(favItem.date)
        eventDate.text = changeFormatDate(date)

        homeTeam.text = favItem.homeTeam
        homeScore.text = favItem.homeScore

        awayScore.text = favItem.awayScore
        awayTeam.text = favItem.awayTeam

        itemView.setOnClickListener { listener(favItem) }
    }
}