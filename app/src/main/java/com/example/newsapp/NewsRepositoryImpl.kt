package com.example.newsapp

class NewsRepositoryImpl(private val api: WallStreetJournalAPI) : NewsRepository {

    override fun getArticles(
        onSuccess: (List<Article>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getArticles(
            onSuccess = { articles ->
                onSuccess(articles)
            },
            onError = { error ->
                onError(error)
            }
        )
    }
}
