package com.devjucelio.allmovies.di

import com.devjucelio.allmovies.repository.HomeDataSource
import com.devjucelio.allmovies.repository.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideHomeDataSource(datasource: HomeDataSourceImpl): HomeDataSource
}