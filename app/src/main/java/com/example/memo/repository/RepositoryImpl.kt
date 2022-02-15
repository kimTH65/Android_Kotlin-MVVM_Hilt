package com.example.memo.repository

import androidx.lifecycle.LiveData
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.model.dto.MovieResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: RetrofitInterface
) : Repository {
    override suspend fun provideRepository(key: String, targetDt: String): Response<List<MovieResponse>> {
        return service.getBoxOffice(key, targetDt)
    }
