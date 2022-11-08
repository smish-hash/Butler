package com.smish.butler.news.data.repository.dataSourceImpl

import com.smish.butler.news.data.api.NewsApiService
import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import com.smish.butler.news.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApi: NewsApiService
): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country: String, page: Int): Response<TopHeadlinesAPIResponse> {
        return newsApi.getTopHeadlines(country, page)
    }
}