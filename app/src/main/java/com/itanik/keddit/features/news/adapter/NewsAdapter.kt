package com.itanik.keddit.features.news.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.itanik.keddit.commons.adapter.ViewType
import com.itanik.keddit.commons.adapter.ViewTypeDelegateAdapter

/**
* Adapter that process news posts
*/
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

}