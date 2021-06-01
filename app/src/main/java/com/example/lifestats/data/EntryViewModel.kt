package com.example.lifestats.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application): AndroidViewModel(application) {
    val getAllEntries: LiveData<List<EntryWithValues>>
    //val getAllEntryWithEntryValue: LiveData<List<EntryWithEntryValue>>
    private val repository: EntryRepository

    init{
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        getAllEntries = repository.getAllEntries
        //getAllEntryWithEntryValue = repository.getAllEntryWithEntryValue
    }

    fun addEntry(entry:Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEntry(entry)
        }
    }

    fun addEntryValue(entryValue: EntryValue){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEntryValue(entryValue)
        }
    }

    // added Week 7 - not working
//    fun addEntryWithEntryValue(entry:Entry, entryValue: EntryValue){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.addEntryWithEntryValues(EntryWithEntryValues(entry, entryValue))
//        }
//    }

    fun updateEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEntry(entry)
        }
    }

    fun updateEntryValue(entryValue: EntryValue){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEntryValue(entryValue)
        }
    }

    fun deleteEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEntry(entry)
        }
    }

    fun getDates(day: Int, month: Int, year: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.getDates(day, month, year)
        }
    }
}