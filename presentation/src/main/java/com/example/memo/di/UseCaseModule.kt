package com.example.memo.di

<<<<<<< HEAD:presentation/src/main/java/com/example/memo/di/UseCaseModule.kt
import com.example.data.repository.RepositoryImpl
import com.example.domain.usecase.UseCase
=======
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
import com.example.memo.usecase.UseCase
>>>>>>> 38e9c44b313752fe15efe8e74e7f51ce98e2beb0:app/src/main/java/com/example/memo/di/UseCaseModule.kt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideUseCase(repositoryImpl: RepositoryImpl) : UseCase = UseCase(repositoryImpl)
}