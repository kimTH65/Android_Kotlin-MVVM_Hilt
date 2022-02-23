package com.example.memo.usecase

import com.example.memo.model.dto.Dto
import com.example.memo.repository.Repository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovie(key: String,targetDt:String): List<Dto> {
        val list = repository.provideRepository(key,targetDt)
        return list
    }
}
