package com.example.lifestats.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "entry_value_table"
/*, foreignKeys = arrayOf(ForeignKey(entity = Entry::class,parentColumns = arrayOf("entryId"),
childColumns = arrayOf("entryId"), onDelete = 5, onUpdate = 5, deferred = false))*/
)
data class EntryValue (
    @PrimaryKey (autoGenerate = true)
    val entryValId: Int,
    val entryId: Int,
    val entry_value: Long
): Parcelable