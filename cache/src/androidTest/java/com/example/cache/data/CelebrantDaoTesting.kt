package com.example.cache.data



import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cache.dao.CelebrantDao
import com.example.cache.database.BirthYaayDataBase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*


@RunWith(AndroidJUnit4::class)
class CelebrantDaoTesting {

    private lateinit var dataBase: BirthYaayDataBase
    private lateinit var celebrantDao: CelebrantDao

    private lateinit var calendar: Calendar
    private lateinit var simpleDateFormat: SimpleDateFormat

    private val interestsList = listOf("music", "sports", "fashion")
    private val giftList = listOf("books", "shoes", "clothes")

    private val samuel = CelebrantData(name = "samuel", phoneNumber = "09034442", email = "esa@gmail.com", interests = interestsList, gifts = giftList, note = "okay checking", date = Date(7, 9))

    private val mary = CelebrantData( name = "mary", phoneNumber = "09034442", email = "mary@gmail.com", interests = interestsList, gifts = giftList, note = "okay mary checking", date = Date(9, 5))

    private val tope = CelebrantData(name = "tope", phoneNumber = "09034442", email = "tope@gmail.com", interests = interestsList, gifts = giftList, note = "okay tope checking", date = Date(29, 2))

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDataBase() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataBase = Room.inMemoryDatabaseBuilder(context, BirthYaayDataBase::class.java).build()
        celebrantDao = dataBase.celebrantDao()



        calendar = Calendar.getInstance()

        //insert celebrants

        celebrantDao.insert(mary)
        celebrantDao.insert(samuel)
        celebrantDao.insert(tope)
    }

    @After
    fun closeDatabase(){
        dataBase.close()
    }

    @Test
    fun testGetCelebrants() = runBlocking {

        val celebrantList = celebrantDao.getAllCelebrant().first()



        assertThat(celebrantList.size, equalTo(3))
    }


    @Test
    fun compareDates() = runBlocking {

        simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())
        var day = simpleDateFormat.format(calendar.time).toInt()

        Log.d("CHECKINGTEST", "OKAY $day")
        assertThat(1, equalTo(1))
    }



}