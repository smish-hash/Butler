package com.smish.butler.news.data.repository.dataSource

import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    // here we will define abstract functions to communicate with the API
    suspend fun getTopHeadlines(country: String, page: Int): Response<TopHeadlinesAPIResponse>
}