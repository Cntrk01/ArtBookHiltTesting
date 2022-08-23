package com.example.artbookhilttest.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artbookhilttest.roomdb.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts")
    fun observeArts(): LiveData<List<Art>>

}