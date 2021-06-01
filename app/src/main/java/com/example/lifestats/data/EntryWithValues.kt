package com.example.lifestats.data

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class EntryWithValues(
    @Embedded
    val entry: Entry,

    @Relation(
        parentColumn="entryId",
        entityColumn="entryId"
    )
    val entries: Array<EntryValue>
)