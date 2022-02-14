package com.shong.hilt_mvvm_simple.data

import com.shong.hilt_mvvm_simple.data.db.dao.ExampleDao
import com.shong.hilt_mvvm_simple.data.db.entity.ExampleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository constructor(private val exampleDao: ExampleDao) {

    suspend fun insertExampleEntity(ex: ExampleEntity){
        withContext(Dispatchers.IO){
            exampleDao.insertEx(ex)
        }
    }

    suspend fun getAllExampleEntity(): List<ExampleEntity>{
        val result: List<ExampleEntity>
        withContext(Dispatchers.IO){
            result = exampleDao.getAllEx()
        }
        return result
    }

    suspend fun nukeExampleEntity(){
        withContext(Dispatchers.IO){
            exampleDao.nukeExDB()
        }
    }
}