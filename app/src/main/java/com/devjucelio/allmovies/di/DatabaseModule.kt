package com.devjucelio.allmovies.di

import android.content.Context
import androidx.room.Room
import com.devjucelio.allmovies.AppConstants.DATABASE_NAME
import com.devjucelio.allmovies.database.MyListDao
import com.devjucelio.allmovies.database.RoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(context: Context): RoomDatabase =
        Room.databaseBuilder(context.applicationContext, RoomDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providesMyListDao(db: RoomDatabase): MyListDao {
        return db.MyListDao()
    }
}