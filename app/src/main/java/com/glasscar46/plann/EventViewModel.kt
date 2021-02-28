package com.glasscar46.plann

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private  var _startTime = MutableLiveData("")
        private val startTime : LiveData<String> = _startTime
    private  var duration = 0
      var eventTitle = MutableLiveData("")
         private val _eventTitle : LiveData<String> = eventTitle
        private val  database = Room.databaseBuilder(getApplication<Application>()
            .applicationContext,EventDatabase::class.java, "EventDatabase")
            .fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    //private var _eventList = MutableLiveData<List<Event>>()
    lateinit var eventList : LiveData<List<Event>>
    private  var _eventType = MutableLiveData("")
        val eventType : LiveData<String> = _eventType
      private var _eventDay = MutableLiveData("Select day")
        val eventDay : LiveData<String> = _eventDay
    fun getAllEvents(){
        eventList = database.eventDao().getAllEvent()
    }
    fun saveEvent() {
        if (verifyEventData()){
            database.eventDao().addEvent(_eventTitle.value!!,eventType.value!!,startTime.value!!,duration.toString())
            _eventDay.value = "Select day"
            _eventType.value = ""
            duration = 0
            _startTime.value = ""
            Log.d("database","event created")
        }
        else{
            Log.w("database","event not created ${_eventTitle.value!!},${eventType.value!!},${startTime.value!!},${duration}")
        }
    }
    fun deleteEvent(id:Int){
        database.eventDao().removeEvent(id)
    }
    fun updateEventDay(day : String){ _eventDay.value = day}
    fun updateEventType(type : String){
        _eventType.value = type
    }
    private fun verifyEventData(): Boolean{
        if (duration != 0
            && startTime.value?.isNotEmpty() == true
            && !eventDay.value.equals("select day",true)
            && !eventType.value.isNullOrEmpty()
            && eventTitle.value?.isNotEmpty() == true
        ){
            return true
        }
        return false
    }
    fun saveEventTime(startTime: String, duration: Int){
        this.duration = duration
        _startTime.value = startTime
    }

}