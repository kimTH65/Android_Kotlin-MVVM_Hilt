package com.example.data.model.api

<<<<<<< HEAD:data/src/main/java/com/example/data/model/api/RetrofitInterface.kt
import com.example.data.model.response.MovieResponse
=======
import androidx.lifecycle.LiveData
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.MovieResponse
import retrofit2.Call
>>>>>>> 38e9c44b313752fe15efe8e74e7f51ce98e2beb0:app/src/main/java/com/example/memo/model/dto/request/RetrofitInterface.kt
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getBoxOffice( // suspend = Coroutine사용
        @Query("key") key: String?,
        @Query("targetDt") targetDt: String?
    ): Response<MovieResponse>
}