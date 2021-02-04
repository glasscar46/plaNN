package com.glasscar46.plann

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room

class EventViewModel(context: Context) : ViewModel() {
        private val  database = Room.databaseBuilder(context,EventDatabase::class.java,"EventDatabase").build()
    private var _eventList = MutableLiveData<List<Event>>()
        val eventList : LiveData<List<Event>> = _eventList
    private  var _eventType = MutableLiveData<String>("")
        val eventType : LiveData<String> = _eventType
    fun getAllEvents(){
        _eventList = MutableLiveData(database.eventDao().getAllEvent())
    }
    fun saveEvent(eventName: String,eventType: String, eventStart: String, eventEnd: String){
        database.eventDao().addEvent(eventName,eventType,eventStart,eventEnd)
    }
    fun deleteEvent(id:Int){
        database.eventDao().removeEvent(id)
    }
    fun updateEventType(type : String){
        _eventType.value = type
    }

}