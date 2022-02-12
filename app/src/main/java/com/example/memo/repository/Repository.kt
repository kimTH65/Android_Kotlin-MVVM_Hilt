package com.example.memo.repository

import com.example.memo.model.dto.MovieResponse

interface Repository {
    suspend fun getGithub(remoteErrorEmitter: RemoteErrorEmitter, owner : String): List<MovieResponse>

}