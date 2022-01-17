package com.olivares.dogapp.di

import com.olivares.dogapp.data.DogsRepository
import com.olivares.dogapp.data.DogsRepositoryData
import com.olivares.dogapp.data.remote.DogsDataSource
import com.olivares.dogapp.data.remote.DogsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideDogsRepository(repository: DogsRepositoryData): DogsRepository


    @Binds
    abstract fun provideDogsDataSource(datasource: DogsRemoteDataSource): DogsDataSource
}