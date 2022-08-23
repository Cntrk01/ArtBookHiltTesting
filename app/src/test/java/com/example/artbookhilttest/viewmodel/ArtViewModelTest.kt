package com.example.artbookhilttest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbookhilttest.MainCoroutineRule
import com.example.artbookhilttest.getOrAwaitValueTest
import com.example.artbookhilttest.repo.FakeArtRepository
import com.example.artbookhilttest.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {
    @get:Rule //kurallarımızı belirledik burda.Yani diğer util sınıfında yazdığımız kodları çalıştırıcaz
    var instantTaskExecutorRule=InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule=MainCoroutineRule()
    private lateinit var viewModel: ArtViewModel
    @Before
    fun setup(){
        //Test Doubles
        viewModel=ArtViewModel(FakeArtRepository())
    }
    @Test
    fun `Data Eklicez Year Vermicez Hata Döndürecek`(){
        viewModel.makeArt("Mona Lisa","Davinci","")
        //Livedata geldi.Bu asenkron çalışıyor.Bizde bunu normal dataya çeviricez.Burada herşey main threadde çalışıcak!Rule ile getOr fonksiyonunu aldık
        val value=viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `Data Eklicez Name Vermicez Hata Döndürecek`(){
        viewModel.makeArt("","Davinci","1800")
        val value=viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `Data Eklicez ArtName Vermicez Hata Döndürecek`(){
        viewModel.makeArt("Mona Lisa","","1234")
        val value=viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}