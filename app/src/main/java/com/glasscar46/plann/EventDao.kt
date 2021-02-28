package com.glasscar46.plann

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao  {
    @Insert
    fun addEvent(event: Event)
    @Query("delete from Event where id = :id")
    fun removeEvent(id: Int)
    @Query("select * from Event ")
    fun getAllEvent(): LiveData<List<Event>>

    @Query("insert into Event (EventName,EventType,StartTime,EndTime)values(:eventName,:eventType,:startTime,:endTime)")
    fun addEvent(eventName: String,eventType: String, startTime: String, endTime: String)
}