package com.smish.butler.news.domain.usecase

import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNews(private val newsRepository: NewsRepository) {
    fun getSavedNews(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}