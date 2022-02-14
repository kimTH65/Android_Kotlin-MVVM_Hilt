package com.example.memo.usecase

import com.example.memo.ApiResult
import retrofit2.Response

open class BaseRepository {

    internal suspend fun <T> getResponse(response: Response<T>): ApiResult<T> {
        return try {
            if (response.isSuccessful) {
                return ApiResult.success("000000", response.body())
            } else {
                val code =  ""
                val message = ""
                ApiResult.error(code, message)
            }
        } catch (e: Exception) {
            ApiResult.error(e)
        }
    }
}