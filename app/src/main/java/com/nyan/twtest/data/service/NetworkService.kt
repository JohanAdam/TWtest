package com.nyan.twtest.data.service

import com.nyan.twtest.data.model.details.DetailsResp
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon/{id}")
    suspend fun getDetails(
        @Path("id") id: Int): DetailsResp

}