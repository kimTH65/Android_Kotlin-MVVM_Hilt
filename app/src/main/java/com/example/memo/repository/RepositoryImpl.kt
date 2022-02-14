package com.example.memo.repository

import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.model.dto.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: RetrofitInterface
) : Repository {
    override suspend fun provideRepository(key: String, targetDt: String): Response<MovieResponse> {
        return service.getBoxOffice(key,targetDt)
    }
}