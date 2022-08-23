package com.example.artbookhilttest.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbookhilttest.roomdb.Art
import com.example.artbookhilttest.roomdb.ArtDao

@Database(entities = [Art::class],version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}
