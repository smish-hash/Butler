package com.smish.butler.news.domain.usecase

import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import com.smish.butler.news.domain.repository.NewsRepository
import com.smish.butler.util.Resource

class GetNewsHeadlines(private val newsRepository: NewsRepository) {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<TopHeadlinesAPIResponse>{
        // we can also use the .map to convert the data from repo to another type (coin)
        return newsRepository.getNewsHeadlines(country, page)
    }
}