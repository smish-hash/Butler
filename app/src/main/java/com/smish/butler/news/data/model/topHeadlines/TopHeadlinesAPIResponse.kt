package com.smish.butler.news.data.model.topHeadlines


import com.google.gson.annotations.SerializedName

data class TopHeadlinesAPIResponse(
    @SerializedName("articles")
    val articles: List<Article>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
)