package com.example.cache.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Celebrant data entity that represents a single celebrant
 */

@Entity
data class CelebrantData(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "phoneNumber") val phoneNumber: String = "",
        @ColumnInfo(name= "date") val date: Date ,
        @ColumnInfo(name = "email") val email: String = "" ,
        @ColumnInfo(name= "interests") val interests: List<String> = listOf(),
        @ColumnInfo(name ="gifts") val gifts: List<String> = listOf(),
        @ColumnInfo(name = "note") val note: String = ""

        )


  data class Date(
          val day: Int,
          val month: Int
  )