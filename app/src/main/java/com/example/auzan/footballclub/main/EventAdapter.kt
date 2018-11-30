package com.example.auzan.footballclub.main

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

class EventAdapter (private val eventItems: List<EventItem>)
    : RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder (
            EventUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = eventItems.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(eventItems[position])
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

                            textView {
                                id = tv_home_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = "home"
                            }.lparams(matchParent, wrapContent, 1f)

                            linearLayout {
                                gravity = Gravity.CENTER_VERTICAL

                                textView {
                                    id = tv_home_score
                                    padding = dip(8)
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                    text = "0"
                                }

                                textView {
                                    text = "vs"
                                }

                                textView {
                                    id = tv_away_score
                                    padding = dip(8)
                                    textSize = 20f
                                    setTypeface(null, Typeface.BOLD)
                                    text = "0"
                                }
                            }

                            textView {
                                id = tv_away_team
                                gravity = Gravity.CENTER
                                textSize = 18f
                                text = "away"
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

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventDate: TextView = view.find(tv_event_date)
    private val homeTeam: TextView = view.find(tv_home_team)
    private val homeScore: TextView = view.find(tv_home_score)
    private val awayScore: TextView = view.find(tv_away_score)
    private val awayTeam: TextView = view.find(tv_away_team)

    fun bindItem(eventItem: EventItem) {
        val date = strToDate(eventItem.eventDate)
        eventDate.text = changeFormatDate(date)

        homeTeam.text = eventItem.homeTeam
        homeScore.text = eventItem.homeScore

        awayScore.text = eventItem.awayScore
        awayTeam.text = eventItem.awayTeam

    }
}