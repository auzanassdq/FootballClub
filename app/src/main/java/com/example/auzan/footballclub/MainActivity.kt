package com.example.auzan.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        const val PARCELABLE_CLUB = "ItemData"
    }

    private var items: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI(items).setContentView(this)

        iniData()
    }

    class MainActivityUI(private val items: List<Club>) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, wrapContent)

                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ClubAdapter(items) {
                        startActivity<DetailActivity>(PARCELABLE_CLUB to it)
                    }
                }
            }
        }
    }

    private fun iniData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)

        items.clear()
        for (i in name.indices){
            items.add(Club(name[i], image.getResourceId(i, 0), desc[i]))
        }
        image.recycle()
    }

}
