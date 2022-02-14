package com.shong.hilt_mvvm_simple.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shong.hilt_mvvm_simple.data.db.dao.ExampleDao
import com.shong.hilt_mvvm_simple.data.db.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao

    companion object {
        const val DATABASE_NAME = "example.db"
    }

}