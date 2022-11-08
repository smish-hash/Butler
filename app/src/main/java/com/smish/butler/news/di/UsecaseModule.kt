package com.smish.butler.news.di

import com.smish.butler.news.domain.repository.NewsRepository
import com.smish.butler.news.domain.usecase.GetNewsHeadlines
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Singleton
    @Provides
    fun provideNewsHeadlinesUsecase(newsRepository: NewsRepository): GetNewsHeadlines {
        return GetNewsHeadlines(newsRepository)
    }
}