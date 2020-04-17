package com.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    companion object {
        private var INSTANSE : RoomDb? = null

        fun getInstance(context: Context) : RoomDb? {
            if (INSTANSE == null) {
                synchronized(RoomDb::class) {
                    INSTANSE = Room.databaseBuilder(context, RoomDb::class.java, "todo")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANSE
        }
    }
}