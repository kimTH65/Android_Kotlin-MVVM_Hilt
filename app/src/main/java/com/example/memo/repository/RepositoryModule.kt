package com.example.memo.repository

import com.example.memo.model.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module // hilt module
@InstallIn(SingletonComponent::class) //hilt모듈을 설치할 안드로이드 컴포넌트
object  RepositoryModule {
    @Inject private val retrofitApi = NetworkModule.provideInstance(
        "http://www.kobis.or.kr",
        NetworkModule.provideHttpClient(),
        NetworkModule.provideConverterFactory()
    )
    @Provides
    @Singleton
    suspend fun provideRepository(key: String,targetDt: String) = retrofitApi?.getBoxOffice(key, targetDt)


}
