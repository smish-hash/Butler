package com.smish.butler.news.di

import com.smish.butler.news.data.repository.NewsRepositoryImpl
import com.smish.butler.news.data.repository.dataSource.NewsRemoteDataSource
import com.smish.butler.news.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import com.smish.butler.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}