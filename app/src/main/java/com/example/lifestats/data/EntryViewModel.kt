package com.example.lifestats.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(application: Application): AndroidViewModel(application) {
    val getAllEntries: LiveData<List<Entry>>
    private val repository: EntryRepository

    init{
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        getAllEntries = repository.getAllEntries
    }

    fun addEntry(entry:Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEntry(entry)
        }
    }

    fun updateEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEntry(entry)
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