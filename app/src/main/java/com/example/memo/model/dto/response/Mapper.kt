package com.example.memo.model.dto.response

import android.graphics.Movie
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.MovieResponse

object Mapper {
    fun mapperData(response: List<Dto>?) : List<Dto>?{
        return if (response !=null){
            response.toDomain()
        }else null
    }

    fun List<Dto>.toDomain() : List<Dto>{
        return this.map {
            Dto(it.rank,it.openDt,it.movieNm)
        }
    }
}