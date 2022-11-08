package com.smish.butler.news.domain.repository

import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import com.smish.butler.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<TopHeadlinesAPIResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<TopHeadlinesAPIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)

    // get data from db as a flow, to get the data stream asynchronously
    // in the viewmodel, we will collect this stream of data flow and emit it as a live data
    // we do not use suspend as we do not want to pause or resume it
    fun getSavedNews(): Flow<List<Article>>
}