package com.example.memo.usecase

import com.example.memo.repository.Repository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) : BaseRepository(){

    suspend fun getMovie(key: String,targetDt:String) {
        val list = repository.provideRepository(key,targetDt)


    }
}
