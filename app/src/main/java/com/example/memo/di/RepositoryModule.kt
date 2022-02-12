package com.example.memo.di

import com.example.memo.model.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module // hilt module
@InstallIn(SingletonComponent::class) //hilt모듈을 설치할 안드로이드 컴포넌트
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}