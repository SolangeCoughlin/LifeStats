package com.example.lifestats.data

import androidx.room.Embedded
import androidx.room.Relation


data class EntryWithEntryValue (
    @Embedded val entry: Entry,
    @Relation(
        parentColumn = "entryId",
        entityColumn = "entryId"
    )
    val entryValue: List<EntryValue>?
)