package com.example.myapplication.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserAccount::class], version = 1)
abstract class MYDataBase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    companion object {
        var INSTANCE: MYDataBase? = null

        fun getAppDataBase(context: Context): MYDataBase? {
            if (INSTANCE == null) {
                synchronized(MYDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MYDataBase::class.java, "MYDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}
