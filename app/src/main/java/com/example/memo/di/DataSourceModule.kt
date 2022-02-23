package com.example.memo.di

import com.example.memo.model.dto.DataSource
import com.example.memo.model.dto.DataSourceImpl
import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(retrofitInterface: RetrofitInterface): DataSource = DataSourceImpl(retrofitInterface)
}