package com.example.lifestats.data

import androidx.lifecycle.LiveData

class EntryRepository(private val entryDao: EntryDAO) {

    val getAllEntries: LiveData<List<Entry>> = entryDao.getAllEntries()

    //Added Week 7
    val getAllEntryWithEntryValue: LiveData<List<EntryWithEntryValue>> = entryDao.getAllEntriesWithValues()

    //Added Week 7 - not working
//    suspend fun addEntryWithEntryValues(entryWithEntryValues: EntryWithEntryValues){
//        entryDao.addEntryWithEntryValues(entryWithEntryValues)
//    }

    suspend fun addEntryValue(entryValue: EntryValue){
        entryDao.addEntryValue(entryValue)
    }

    suspend fun addEntry(entry: Entry){
        entryDao.addEntry(entry)
    }

    suspend fun updateEntry(entry: Entry){
        entryDao.updateEntry(entry)
    }

    suspend fun deleteEntry(entry:Entry){
        entryDao.deleteEntry(entry)
    }
}