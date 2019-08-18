package com.itanik.keddit.features.news

import com.itanik.keddit.api.RestAPI
import com.itanik.keddit.commons.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(limit : Int = 10): Observable<List<RedditNewsItem>> {
        return Observable.create {
                subscriber ->
            val callResponse = api.getNews("", limit.toString())
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val news = response.body()?.data?.children?.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                        item.created, item.thumbnail, item.url)
                }
                subscriber.onNext(news!!)
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}