package com.example.cache.unit

import com.example.cache.converters.DateConverter
import com.example.cache.converters.StringListConverter
import com.example.cache.data.Date
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * For testing the converters class
 */


class ConvertersTest {

    private val interestsList = listOf("music", "sports", "fashion")
    private val interestsString = "music,sports,fashion"

    private val dateString = "7,2"
    private val date = Date(7,2)

    @Test
    fun stringToList(){
     assertEquals(interestsList, StringListConverter().fromString(interestsString))
    }

    @Test
    fun listToString(){
        assertEquals(interestsString, StringListConverter().toString(interestsList))
    }

    @Test
    fun dateToString(){
        assertEquals(dateString, DateConverter().toString(date))
    }

    @Test
    fun stringToDate(){
        assertEquals(date , DateConverter().toDate(dateString))
    }

}