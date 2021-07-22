package com.example.birthyaay.util

object Converters {
    fun toString(stringList: List<String>): String{

        return stringList.joinToString(", ")
    }
    fun fromString(string: String): List<String>{

        return string.split(",").map { it }
    }
}