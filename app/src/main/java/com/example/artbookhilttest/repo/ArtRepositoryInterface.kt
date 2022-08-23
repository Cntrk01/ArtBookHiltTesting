package com.example.artbookhilttest.repo

import androidx.lifecycle.LiveData
import com.atilsamancioglu.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttest.roomdb.Art
import com.example.artbookhilttest.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}