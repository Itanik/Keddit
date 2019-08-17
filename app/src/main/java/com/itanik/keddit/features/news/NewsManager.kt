package com.itanik.keddit.features.news

import com.itanik.keddit.commons.RedditNewsItem
import io.reactivex.Observable

class NewsManager {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create {
                subscriber ->

            val news = (1..10).map { RedditNewsItem(
                    "Author$it",
                    "Title $it",
                    it, // number of comments
                    1457207701L - it * 200000, // time
                "https://picsum.photos/200/200?image=$it",
                    "url"
                )
            }
            subscriber.onNext(news)
        }
    }
}