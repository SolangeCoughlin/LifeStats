package com.example.lifestats.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Code created with the help of Youtube tutorial by Stevzda-San: https://www.youtube.com/watch?v=lwAvI3WDXBY&list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o

@Database(entities = [Entry::class, EntryValue::class], version = 1, exportSchema = false)
abstract class EntryDatabase: RoomDatabase() {
    abstract fun entryDao():EntryDAO

    companion object{
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getDatabase(context: Context): EntryDatabase{
            // create a temporary instance of the database
            val thisInstance = INSTANCE

            // if this temp instance is not null, the database already exists, so return it
            if (thisInstance != null){
                return thisInstance
            }

            // if it is null, create the database and return it
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "entry_database"
                ).build()
                 INSTANCE = instance
                return instance
            }
        }
    }
}