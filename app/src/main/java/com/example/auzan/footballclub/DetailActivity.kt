package com.example.auzan.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

/**
 * Created by auzan on 11/18/2018.
 * Github: @auzanassdq
 */

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = intent.getParcelableExtra<Club>(MainActivity.PARCELABLE_CLUB)

        DetailActivityUI(item).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    class DetailActivityUI(private val clubItem: Club) : AnkoComponent<DetailActivity> {

        companion object {
            const val imgClub = 1
            const val tvNameClub = 2
        }

        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            relativeLayout{
                lparams(matchParent, matchParent)
                padding = dip(16)

                imageView {
                    id = imgClub
                    clubItem.image?.let {
                        Picasso.get()
                            .load(it)
                            .fit()
                            .into(this)
                    }
                }.lparams(dip(100), dip(100)){
                    centerHorizontally()
                    bottomMargin = dip(16)
                }

                textView {
                    id = tvNameClub
                    text = clubItem.name
                    textSize = 24F
                }.lparams{
                    below(imgClub)
                    centerHorizontally()
                    bottomMargin = dip(24)
                }

                textView {
                    text = clubItem.desc
                }.lparams{
                    below(tvNameClub)
                }
            }
        }

    }
}