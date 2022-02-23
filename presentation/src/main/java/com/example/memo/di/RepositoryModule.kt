package com.example.memo.di

<<<<<<< HEAD:presentation/src/main/java/com/example/memo/di/RepositoryModule.kt
import com.example.data.datasource.DataSourceImpl
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
=======
import com.example.memo.model.dto.DataSourceImpl
import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
>>>>>>> 38e9c44b313752fe15efe8e74e7f51ce98e2beb0:app/src/main/java/com/example/memo/di/RepositoryModule.kt
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
    fun provideRepository(dataSourceImpl: DataSourceImpl): Repository = RepositoryImpl(dataSourceImpl)
}