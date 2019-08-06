package com.itanik.keddit.features.news.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.itanik.keddit.commons.RedditNewsItem
import com.itanik.keddit.commons.adapter.AdapterConstants
import com.itanik.keddit.commons.adapter.ViewType
import com.itanik.keddit.commons.adapter.ViewTypeDelegateAdapter

/**
* Adapter that process news posts
*/
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items.add(loadingItem)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {
        // first remove loading and notify
        val initPosition = items.lastIndex
        //items.removeAt(initPosition)
        //notifyItemRemoved(initPosition)
        // insert news and the loading at the end of the list
        //items.addAll(news)
        items.addAll(initPosition, news)
        //items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }
}