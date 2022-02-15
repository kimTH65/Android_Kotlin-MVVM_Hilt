package com.example.memo.repository

import androidx.lifecycle.LiveData
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.MovieResponse
import retrofit2.Call
import retrofit2.Response

interface Repository {

    suspend fun provideRepository(key: String,targetDt: String) : Response<List<MovieResponse>>

}
