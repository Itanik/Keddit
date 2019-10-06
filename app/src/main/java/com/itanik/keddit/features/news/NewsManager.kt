package com.itanik.keddit.features.news

import com.itanik.keddit.api.RestAPI
import com.itanik.keddit.commons.RedditNews
import com.itanik.keddit.commons.RedditNewsItem
import io.reactivex.Observable

/**
* Позволяет получать новости от Reddit API
 * */

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
                subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body()?.data
                val news = dataResponse?.children?.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                        item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                    dataResponse?.after ?: "",
                    dataResponse?.before ?: "",
                    news!!)

                subscriber.onNext(redditNews)
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}