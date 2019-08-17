package com.itanik.keddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.itanik.keddit.commons.RedditNewsItem
import com.itanik.keddit.commons.RxBaseFragment
import com.itanik.keddit.commons.extensions.inflate
import com.itanik.keddit.features.news.NewsManager
import com.itanik.keddit.features.news.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : RxBaseFragment() {
    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list?.setHasFixedSize(true) // оптимизирует скорость разворачиваия списка
        news_list?.layoutManager = LinearLayoutManager(context)
        initAdapter()
        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews()
            .subscribeOn(Schedulers.io())
            .subscribe (
                { retrievedNews ->
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews)
                },
                { e ->
                    Snackbar.make(news_list, e.message ?: "Something went wrong", Snackbar.LENGTH_LONG).show()
                }
            )
        subscriptions.add(subscription)
    }
}