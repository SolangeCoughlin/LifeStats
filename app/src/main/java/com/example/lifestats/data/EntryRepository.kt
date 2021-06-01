package com.example.lifestats.data

import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDAO) {

    val getAllEntries: LiveData<List<EntryWithValues>> = entryDao.getAllEntries()

    //val getDates: LiveData<List<Entry>> = entryDao.getDates(day: Int,  month: Int, year:Int)

    fun getDates(day: Int, month: Int, year: Int){
        val getDates: LiveData<List<Entry>> = entryDao.getDates(day, month, year)
    }

    //Added Week 7 - not working
//    suspend fun addEntryWithEntryValues(entryWithEntryValues: EntryWithEntryValues){
//        entryDao.addEntryWithEntryValues(entryWithEntryValues)
//    }

    suspend fun addEntry(entry: Entry){
        entryDao.addEntry(entry)
    }

    suspend fun addEntryValue(entryValue: EntryValue){
        entryDao.addEntryValue(entryValue)
    }

    suspend fun updateEntry(entry: Entry){
        entryDao.updateEntry(entry)
    }

    suspend fun updateEntryValue(entryValue: EntryValue){
        entryDao.updateEntryValue(entryValue)
    }

    suspend fun deleteEntry(entry:Entry){
        entryDao.deleteEntry(entry)
    }
}