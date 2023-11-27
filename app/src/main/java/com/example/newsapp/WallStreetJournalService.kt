package com.example.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallStreetJournalService {
    @GET("everything")
    fun getArticles(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): Call<ApiResponse>
}