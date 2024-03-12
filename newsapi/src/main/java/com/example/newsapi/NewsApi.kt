package com.example.newsapi

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/everything")
    fun everything(
        @Query("q") query: String? = null,

    )
}