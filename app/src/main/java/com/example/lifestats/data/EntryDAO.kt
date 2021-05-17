package com.example.lifestats.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntryDAO {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntry(entry: Entry)

    //Week 7 addition - not working
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addEntryWithEntryValues(entryWithEntryValues: EntryWithEntryValues)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEntryValue(entryValue: EntryValue)

    @Delete
    suspend fun deleteEntry(entry:Entry)

    @Update
    suspend fun updateEntry(entry: Entry)

    @Query("SELECT * FROM entry_table ORDER BY entryId ASC")
    fun getAllEntries(): LiveData<List<Entry>>

    @Transaction
    @Query("SELECT * FROM entry_table")
    fun getAllEntriesWithValues(): LiveData<List<EntryWithEntryValue>>
}