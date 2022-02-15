package com.example.memo.repository

import androidx.lifecycle.LiveData
import com.example.memo.model.dto.DataSource
import com.example.memo.model.dto.DataSourceImpl
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.model.dto.MovieResponse
import com.example.memo.model.dto.response.Mapper
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {
    override suspend fun provideRepository(key: String, targetDt: String): List<Dto> {
        return Mapper.mapperData(dataSource.getDataSource(key,targetDt))!!
    }
}
