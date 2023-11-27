package com.example.newsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Parcelable

@Parcelize
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable
