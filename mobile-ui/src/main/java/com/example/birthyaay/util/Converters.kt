package com.example.birthyaay.util

object Converters {
    fun toString(stringList: List<String>): String{

        return stringList.joinToString(", ")
    }
}