package com.glasscar46.plann

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Event::class],version = 1)
abstract class EventDatabase: RoomDatabase() {
    abstract  fun eventDao(): EventDao
}