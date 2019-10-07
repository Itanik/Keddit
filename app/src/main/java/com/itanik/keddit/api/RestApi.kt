package com.itanik.keddit.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
/**
 * Класс для создания и управления запросами к серверу
 */

class NewsRestAPI : NewsAPI {

    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com") //TODO() вывести URL в константы
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}