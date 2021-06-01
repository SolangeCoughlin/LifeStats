package com.example.lifestats.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(foreignKeys = [
    ForeignKey(
        entity = Entry::class,
        parentColumns = ["entryId"],
        childColumns = ["entryId"]
        )
    ]
)
data class EntryValue (
    @PrimaryKey (autoGenerate = true)
    val entryValId: Int,
    val entryId: Int,
    val entry_value: Long,
    val value_unit: String
): Parcelable