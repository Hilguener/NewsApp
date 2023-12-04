package com.example.newsapp.presentation

import com.example.newsapp.model.Article
import com.example.newsapp.datasource.ListArticleCallback
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.datasource.NewsRemoteDataSource

class HomePresenter(private val view: NewsActivity, private val datasource: NewsRemoteDataSource):
    ListArticleCallback {
    override fun onSucess(response: List<Article>) {
        //datasource
        val news = response.map { it }
        view.showNews(news)
    }

    fun findAllNews(){
        view.showProgressBar()
        datasource.findAllNews(this)
    }
    override fun onFailure(message: String) {
        view.onError(message)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}