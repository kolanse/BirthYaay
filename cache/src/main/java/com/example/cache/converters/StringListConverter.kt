package com.example.cache.converters

import androidx.room.TypeConverter


/**
 * Converts a list of type string to string to enable room
 * savings as room doesn't accept lists as a type
 */
class StringListConverter {

    @TypeConverter
    fun fromString(string: String): List<String>{

        return string.split(",").map { it }
    }


    @TypeConverter
    fun toString(stringList: List<String>): String{

        return stringList.joinToString(",")
    }
}