package com.example.newsapp.datasource

import com.example.newsapp.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRemoteDataSource() {
    private val DOMAIN = "wsj.com"

    fun findAllNews(callback: ListArticleCallback) {
        HTTPClient.retrofit()
            .create(WallStreetJournalAPI::class.java)
            .getArticles(DOMAIN, HTTPClient.API_KEY)
            .enqueue(object : Callback<ApiResponse> { // Alterado o tipo do Callback para ApiResponse
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful){
                        val apiResponse = response.body()
                        val articles = apiResponse?.articles ?: emptyList()
                        callback.onSucess(articles)
                    } else {
                        val error = response.errorBody()?.toString()
                        callback.onFailure(error ?: "Erro na chamada")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    callback.onFailure(t.message ?: "Erro interno")
                    callback.onComplete()
                }
            })
    }
}
