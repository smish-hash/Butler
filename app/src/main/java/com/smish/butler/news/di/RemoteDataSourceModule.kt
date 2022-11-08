package com.smish.butler.news.di

import com.smish.butler.news.data.api.NewsApiService
import com.smish.butler.news.data.repository.dataSource.NewsRemoteDataSource
import com.smish.butler.news.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsApiService: NewsApiService
    ):NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}