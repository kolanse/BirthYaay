package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.data.CelebrantData
import kotlinx.coroutines.flow.Flow


@Dao
interface CelebrantDao {

    @Query("SELECT * FROM Celebrantdata")
     fun getAllCelebrant(): Flow<List<CelebrantData>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(celebrantData: CelebrantData)

}
