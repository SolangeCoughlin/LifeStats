package com.example.lifestats.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Update
    suspend fun updateEntry(entry: Entry)

    @Query("SELECT * FROM entry_table ORDER BY entryId ASC")
    fun getAllEntries(): LiveData<List<Entry>>

    @Query("SELECT * FROM entry_table WHERE day = :day AND month = :month AND year =:year")
    fun getDates(day: Int, month: Int,year: Int): LiveData<List<Entry>>

}