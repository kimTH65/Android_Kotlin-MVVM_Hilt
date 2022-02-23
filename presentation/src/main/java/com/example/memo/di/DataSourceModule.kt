package com.example.memo.di

<<<<<<< HEAD:presentation/src/main/java/com/example/memo/di/DataSourceModule.kt
import com.example.data.datasource.DataSource
import com.example.data.datasource.DataSourceImpl
import com.example.data.model.api.RetrofitInterface
=======
import com.example.memo.model.dto.DataSource
import com.example.memo.model.dto.DataSourceImpl
import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
>>>>>>> 38e9c44b313752fe15efe8e74e7f51ce98e2beb0:app/src/main/java/com/example/memo/di/DataSourceModule.kt
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