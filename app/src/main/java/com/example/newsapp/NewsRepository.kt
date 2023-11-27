package com.example.newsapp

interface NewsRepository {
    fun getArticles(
        onSuccess: (List<Article>) -> Unit,
        onError: (Throwable) -> Unit
    )
}