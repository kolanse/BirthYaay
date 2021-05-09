package com.example.cache.di

import android.content.Context
import com.example.cache.dao.CelebrantDao
import com.example.cache.database.BirthYaayDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *  Database module: Provides Depedency Injection for Room data
 *  refer to the docs for more info on  room
 *  https://developer.android.com/training/data-storage/room
 *  for pro tips :
 *  https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
 */

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BirthYaayDataBase{

        return BirthYaayDataBase.getDataBase(context)
    }

    @Provides
    fun provideCelebrantDao(birthYaayDataBase: BirthYaayDataBase):CelebrantDao{

        return birthYaayDataBase.celebrantDao()
    }
}