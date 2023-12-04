package com.example.newsapp.datasource

import com.example.newsapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallStreetJournalAPI {
    @GET("everything")
    fun getArticles(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY
    ): Call<ApiResponse>
}