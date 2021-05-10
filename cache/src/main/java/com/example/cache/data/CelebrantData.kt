package com.example.cache.data

import android.util.Log
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
) {
    /**
     * checks whether the current date is the celebrant's birthday
     */

    fun isTodayBirthday(currentDate: Date): Boolean {

        return currentDate.day == date.day && currentDate.month == date.month
    }


    /**
     * Returns the number of day remaining ..
     *
     */
    fun daysRemaining(): Int {

      var yearCurrent =   Calendar.getInstance(Locale.getDefault())
      var dayYearCurrent = yearCurrent.get(Calendar.DAY_OF_YEAR)

      var c = Calendar.getInstance(Locale.getDefault())

        c.set(yearCurrent.get(Calendar.YEAR), date.month -1, date.day)


      var dayYearCelebrant = c.get(Calendar.DAY_OF_YEAR)
        return if (dayYearCelebrant > dayYearCurrent){

            dayYearCelebrant - dayYearCurrent
        } else {

            (dayYearCelebrant - dayYearCurrent) + yearCurrent.getActualMaximum(Calendar.DAY_OF_YEAR)
        }

    }


    override fun toString() = name!!
}


  data class Date(
          val day: Int,
          val month: Int
  )