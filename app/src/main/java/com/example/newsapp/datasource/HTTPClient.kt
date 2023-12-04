package com.example.newsapp.datasource


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {

    private const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "f2e5629f7e4c412f83191239c0c75e9a"

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}