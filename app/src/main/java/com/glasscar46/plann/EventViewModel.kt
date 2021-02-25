package com.glasscar46.plann

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import java.time.Duration

class EventViewModel(context: Context) : ViewModel() {
    private  var startTime = ""
    private  var duration = 0
        private val  database = Room.databaseBuilder(context,EventDatabase::class.java,"EventDatabase").build()
    private var _eventList = MutableLiveData<List<Event>>()
        val eventList : LiveData<List<Event>> = _eventList
    private  var _eventType = MutableLiveData("")
        val eventType : LiveData<String> = _eventType
      private var _eventDay = MutableLiveData("")
        val eventDay : LiveData<String> = _eventDay
    fun getAllEvents(){
        _eventList = MutableLiveData(database.eventDao().getAllEvent())
    }
    fun saveEvent(eventName: String,eventType: String,) {
        database.eventDao().addEvent(eventName,eventType,startTime,duration.toString())
    }
    fun deleteEvent(id:Int){
        database.eventDao().removeEvent(id)
    }
    fun updateEventDay(day : String){ _eventDay.value = day}
    fun updateEventType(type : String){
        _eventType.value = type
    }
    fun saveEventTime(startTime: String, duration: Int){
        this.duration = duration
        this.startTime = startTime
    }

}