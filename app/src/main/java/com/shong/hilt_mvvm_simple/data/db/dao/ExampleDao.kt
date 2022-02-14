package com.shong.hilt_mvvm_simple.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shong.hilt_mvvm_simple.data.db.entity.ExampleEntity

@Dao
interface ExampleDao {
    @Insert
    suspend fun insertEx(vararg ex: ExampleEntity)

    @Query("SELECT * FROM Example ORDER BY id DESC")
    suspend fun getAllEx(): List<ExampleEntity>

    @Query("DELETE FROM Example")
    suspend fun nukeExDB()
}