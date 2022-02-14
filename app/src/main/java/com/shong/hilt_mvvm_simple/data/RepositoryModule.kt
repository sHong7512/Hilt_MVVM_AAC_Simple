package com.shong.hilt_mvvm_simple.data

import android.content.Context
import androidx.room.Room
import com.shong.hilt_mvvm_simple.data.db.AppDatabase
import com.shong.hilt_mvvm_simple.data.db.dao.ExampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideExampleDao(database: AppDatabase) = database.exampleDao()

    @Singleton
    @Provides
    fun provideRepository(exampleDao: ExampleDao) = Repository(exampleDao)

}