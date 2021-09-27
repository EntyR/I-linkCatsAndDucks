package com.enty.ilinkcatsandducks.data.remote

import com.enty.ilinkcatsandducks.data.remote.dto.DuckDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DucksApi {

    @GET(value = "/api/random")
    suspend fun getRandomDuck(): DuckDTO

}