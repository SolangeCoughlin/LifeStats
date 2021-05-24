package com.example.lifestats.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "entry_table")
data class Entry (
    @PrimaryKey(autoGenerate = true)
    val entryId: Int,
    val entry_value: Long,
    val date: String,
    val descrip: String
): Parcelable