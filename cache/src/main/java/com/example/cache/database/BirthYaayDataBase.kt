package com.example.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cache.converters.DateConverter
import com.example.cache.converters.StringListConverter
import com.example.cache.dao.CelebrantDao
import com.example.cache.data.CelebrantData


@Database(entities = [CelebrantData::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class BirthYaayDataBase : RoomDatabase(){

     abstract fun celebrantDao() : CelebrantDao

     companion object{

        @Volatile
        private var INSTANCE : BirthYaayDataBase? = null

        fun getDataBase(context: Context): BirthYaayDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BirthYaayDataBase::class.java,
                    "birthyaay_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

