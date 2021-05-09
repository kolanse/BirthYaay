package com.example.cache.converters

import androidx.room.TypeConverter
import com.example.cache.data.Date

/**
 * Converts a date type  to string to enable room
 * savings as room doesn't accept objects as a type
 */
class DateConverter {

    @TypeConverter
    fun toDate(string: String): Date {

        var date =  string.split(",")

        return Date(date[0].toInt(), date[1].toInt())
    }

    @TypeConverter
    fun toString(date: Date): String{


        return  "${date.day},${date.month}"
    }
}