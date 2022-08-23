package com.example.artbookhilttest.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atilsamancioglu.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttest.roomdb.Art
import com.example.artbookhilttest.util.Resource

class FakeArtRepository:ArtRepositoryInterface {
    private val arts= mutableListOf<Art>()
    private val artsLiveData=MutableLiveData<List<Art>>(arts)
    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    //arts değişkeni içerisindeki verileri artsLiveData ya da ekledik ki boş olmasın
    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}