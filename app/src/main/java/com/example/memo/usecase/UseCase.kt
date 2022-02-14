package com.example.memo.usecase

import com.example.memo.ApiResult
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.MovieResponse
import com.example.memo.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) : BaseRepository() {

    suspend fun getMovie(key: String,targetDt:String) : Flow<ApiResult<MovieResponse>> {
        return flow {
            emit(ApiResult.loading())
            val result = getResponse(repository.provideRepository(key,targetDt))
            emit(result)
        }
    }
}
