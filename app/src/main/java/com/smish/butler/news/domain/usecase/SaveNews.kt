package com.smish.butler.news.domain.usecase

import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.news.domain.repository.NewsRepository

class SaveNews(private val newsRepository: NewsRepository) {
    suspend fun saveNews(article: Article) = newsRepository.saveNews(article)
}