package com.example.newsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WallStreetJournalAPI {
    private val service: WallStreetJournalService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(WallStreetJournalService::class.java)
    }

    fun getArticles(
        onSuccess: (List<Article>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val call = service.getArticles("wsj.com", "f2e5629f7e4c412f83191239c0c75e9a")

        call.enqueue(object : retrofit2.Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: retrofit2.Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    onSuccess(articles)
                } else {
                    onError(Exception("Erro na chamada à API"))
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}
