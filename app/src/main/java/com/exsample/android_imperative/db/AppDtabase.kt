package com.exsample.android_imperative.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exsample.android_imperative.model.TVShow

@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDtabase: RoomDatabase() {

    abstract fun getTVShowDao(): TVShowDao

    companion object{
        private var DB_INSTANCE: AppDtabase? = null

        fun getAppDBInstance(context: Context): AppDtabase{
            if (DB_INSTANCE == null){
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDtabase::class.java,
                    "DB_TV_SHOES"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }

}