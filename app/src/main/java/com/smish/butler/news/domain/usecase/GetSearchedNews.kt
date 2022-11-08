package com.smish.butler.news.domain.usecase

import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import com.smish.butler.news.domain.repository.NewsRepository
import com.smish.butler.util.Resource

class GetSearchedNews(private val newsRepository: NewsRepository) {
    suspend fun getSearchedNews(searchQuery: String): Resource<TopHeadlinesAPIResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}