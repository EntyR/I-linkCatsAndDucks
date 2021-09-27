package com.enty.ilinkcatsandducks.data.remote

import com.enty.ilinkcatsandducks.data.remote.dto.CatDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsApi {

    @GET("/catapi/rest/")
    suspend fun getRandomCat(): CatDTO

}