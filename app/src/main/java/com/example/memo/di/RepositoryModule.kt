package com.example.memo.di

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
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(retrofitInterface: RetrofitInterface): Repository = RepositoryImpl(retrofitInterface)
}