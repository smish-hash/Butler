package com.smish.butler.news.data.repository

import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.news.data.model.topHeadlines.TopHeadlinesAPIResponse
import com.smish.butler.news.data.repository.dataSource.NewsRemoteDataSource
import com.smish.butler.news.domain.repository.NewsRepository
import com.smish.butler.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {

    private fun responseToResource(response: Response<TopHeadlinesAPIResponse>): Resource<TopHeadlinesAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<TopHeadlinesAPIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<TopHeadlinesAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}