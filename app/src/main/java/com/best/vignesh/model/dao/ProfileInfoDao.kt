package com.best.vignesh.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.best.vignesh.model.ProfileInfo

@Dao
interface ProfileInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mCategory :List<ProfileInfo>)

    @Query("SELECT * from ProfileInfo")
    suspend fun getList() : MutableList<ProfileInfo>

    @Query("SELECT * from ProfileInfo")
    fun getSearch() : LiveData<List<ProfileInfo>>

    @Query("DELETE FROM ProfileInfo")
    fun deleteAll()

    /* @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insert(mWorkorder: Purpose): Long



     @Delete
     suspend fun delete(mWorkorder: Purpose): Int

     @Update
     suspend fun update(vararg mWorkorder: Purpose): Int*/

}