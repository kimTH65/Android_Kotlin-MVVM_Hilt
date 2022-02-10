package com.example.memo.model

import com.example.memo.model.dto.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getBoxOffice( // suspend = Coroutine사용
        @Query("key") key: String?,
        @Query("targetDt") targetDt: String?
    ): Call<MovieResponse>
}