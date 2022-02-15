package com.example.memo.model.dto

import com.example.memo.utils.RemoteErrorEmitter

interface DataSource {
    suspend fun getDataSource(
        key: String,
        targetDt: String
    ): List<Dto>?
}