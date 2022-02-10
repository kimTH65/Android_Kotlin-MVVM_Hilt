package com.example.memo.model.dto

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @SerializedName("boxOfficeResult")
    var boxofficeResult: BoxOfficeList?
)

data class BoxOfficeList(
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeList: List<Dto> = arrayListOf()
)

data class Dto(
    @SerializedName("rank")
    var rank : String?,
    @SerializedName("openDt")
    var openDt : String?,
    @SerializedName("movieNm")
    var movieNm : String?,
)