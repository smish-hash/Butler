package com.smish.butler.news.domain.usecase

import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.news.domain.repository.NewsRepository

class DeleteSavedNews(private val newsRepository: NewsRepository) {
    suspend fun deleteNews(article: Article) = newsRepository.deleteNews(article)
}