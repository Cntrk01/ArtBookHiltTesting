package com.example.artbookhilttest.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.artbookhilttest.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule=HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database:ArtDatabase

   // private lateinit var database: ArtDatabase
    private lateinit var dao: ArtDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.artDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArtTesting() =
        runBlocking { //daodaki fonk. suspend olduğu için Coroutine içerisinde çalıştırmamız gerekiyor.
            //Test olanı var ama depcread edilmiş ondan runBlocking kullanıyorum.Bu da olur sıkıntı yok
            val ex = Art("mona", "vinci", 1700, "test.com", 1)
            dao.insertArt(ex)
            val list = dao.observeArts()
                .getOrAwaitValueTest() //Yine LiveData var bundan kurtarmamız lazım.ç.Observer ile de datalarımızı dinliyoruz/.
            assertThat(list).contains(ex)
        }

    @Test
    fun deleteArtTesting() = runBlocking {
        val ex = Art("mona", "vinci", 1700, "test.com", 1)
        dao.insertArt(ex)
        dao.deleteArt(ex)
        val list = dao.observeArts()
            .getOrAwaitValueTest() //Yine LiveData var bundan kurtarmamız lazım.ç.Observer ile de datalarımızı dinliyoruz/.
        assertThat(list).doesNotContain(ex) //data yoksa kontrolünü sagladık burada barındırmıyor kontrolü
    }
}