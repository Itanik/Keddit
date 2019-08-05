package com.itanik.keddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.itanik.keddit.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list?.setHasFixedSize(true) // оптимизирует скорость разворачиваия списка
        news_list?.layoutManager = LinearLayoutManager(context)
        initAdapter()
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }
}