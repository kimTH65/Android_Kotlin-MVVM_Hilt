package com.example.memo.repository

import com.example.memo.model.dto.MovieResponse
import retrofit2.Response

interface Repository {

    suspend fun provideRepository(key: String,targetDt: String) : Response<MovieResponse>

}
