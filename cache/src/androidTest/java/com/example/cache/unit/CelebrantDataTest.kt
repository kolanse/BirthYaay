package com.example.cache.unit

import android.util.Log
import com.example.cache.data.CelebrantData
import com.example.cache.data.Date
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*


/**
 * Testing the methods within celebrantdata class
 */
class CelebrantDataTest {


    private lateinit var dateConst: Date
    private lateinit var mary: CelebrantData
    private lateinit var samuel: CelebrantData
    private val interestsList = listOf("music", "sports", "fashion")
    private val giftList = listOf("books", "shoes", "clothes")
    private lateinit var calendar: Calendar

    @Before
    fun setUp(){
        dateConst = Date(1, 5)
        mary = CelebrantData( name = "mary", phoneNumber = "09034442", email = "mary@gmail.com", interests = interestsList, gifts = giftList, note = "okay mary checking", date = Date(1, 5))
        samuel = CelebrantData(name = "samuel", phoneNumber = "09034442", email = "esa@gmail.com", interests = interestsList, gifts = giftList, note = "okay checking", date = Date(7, 9))
        calendar = Calendar.getInstance(Locale.getDefault())


    }


    @Test
    fun isCelebrantBirthdayTest(){
         assertFalse(samuel.isTodayBirthday(dateConst))
        assertTrue(mary.isTodayBirthday(dateConst))
    }

    @Test
    fun daysRemainingTest(){
        var currentDay = calendar.get(Calendar.DAY_OF_YEAR)
        var maryYear = Calendar.getInstance(Locale.getDefault())
        maryYear.set(Calendar.MONTH, mary.date.month -1)
        maryYear.set(Calendar.DAY_OF_MONTH, mary.date.day)
        var maryDay = maryYear.get(Calendar.DAY_OF_YEAR)

        if (maryDay > currentDay){
            assertEquals(maryDay - currentDay, mary.daysRemaining())

        } else{
           var daysLeft =  (maryDay - currentDay) + calendar.getActualMaximum(Calendar.DAY_OF_YEAR)

            Log.d("THETESTING", "IT CALLS HERE $daysLeft")

            assertEquals(daysLeft, mary.daysRemaining())
        }

    }

    @Test
    fun testToString(){
        assertEquals("samuel", samuel.toString() )
        assertEquals("mary", mary.toString())
    }
}