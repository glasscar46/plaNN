package com.glasscar46.plann

import androidx.room.ColumnInfo
import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName = "Event")
data class Event(@PrimaryKey(autoGenerate = true) val id: Int,
                 @ColumnInfo(name = "EventName") var eventName: String,
                 @ColumnInfo(name = "EventType") var eventType : String,
                 @ColumnInfo(name = "StartTime") var startTime: String,
                 @ColumnInfo(name = "EndTime") var endTime: String)
