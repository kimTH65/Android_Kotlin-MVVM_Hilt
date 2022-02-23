package com.example.memo.model.dto

import com.example.memo.model.dto.request.RetrofitInterface
import com.example.memo.utils.RemoteErrorEmitter
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : DataSource{
    override suspend fun getDataSource(
        key: String,
        targetDt: String
    ): List<Dto> {
        val result= retrofitInterface.getBoxOffice(key,targetDt)

        if(result.isSuccessful){
            val response = result.body()
            val list : List<Dto> = response!!.boxofficeResult!!.dailyBoxOfficeList
            return list
        }else{
            return emptyList()
        }
    }
}