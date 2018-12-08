package com.example.auzan.footballclub.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by auzan on 12/4/2018.
 * Github: @auzanassdq
 */

class FavoriteOpenHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {
    companion object {
        private var instance: FavoriteOpenHelper? = null

        @Synchronized
        fun getInstance (ctx: Context): FavoriteOpenHelper {
            if (instance == null) {
                instance = FavoriteOpenHelper(ctx.applicationContext)
            }
            return instance as FavoriteOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITES, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.ID_EVENT to TEXT,
            Favorite.DATE to TEXT,

            // home team
            Favorite.HOME_ID to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.HOME_FORMATION to TEXT,
            Favorite.HOME_GOAL_DETAILS to TEXT,
            Favorite.HOME_SHOTS to TEXT,
            Favorite.HOME_LINEUP_GOALKEEPER to TEXT,
            Favorite.HOME_LINEUP_DEFENSE to TEXT,
            Favorite.HOME_LINEUP_MIDFIELD to TEXT,
            Favorite.HOME_LINEUP_FORWARD to TEXT,
            Favorite.HOME_LINEUP_SUBSTITUTES to TEXT,

            // away team
            Favorite.AWAY_ID to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.AWAY_FORMATION to TEXT,
            Favorite.AWAY_GOAL_DETAILS to TEXT,
            Favorite.AWAY_SHOTS to TEXT,
            Favorite.AWAY_LINEUP_GOALKEEPER to TEXT,
            Favorite.AWAY_LINEUP_DEFENSE to TEXT,
            Favorite.AWAY_LINEUP_MIDFIELD to TEXT,
            Favorite.AWAY_LINEUP_FORWARD to TEXT,
            Favorite.AWAY_LINEUP_SUBSTITUTES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITES, true)
    }

}

val Context.database: FavoriteOpenHelper
    get() = FavoriteOpenHelper.getInstance(applicationContext)