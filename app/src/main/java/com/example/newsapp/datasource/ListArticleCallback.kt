package com.example.newsapp.datasource

import com.example.newsapp.model.Article

interface ListArticleCallback {
    fun onSucess(response: List<Article>)
    fun onFailure(message: String)
    fun onComplete()
}