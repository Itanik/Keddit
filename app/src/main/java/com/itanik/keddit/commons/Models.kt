package com.itanik.keddit.commons

import com.itanik.keddit.commons.adapter.AdapterConstants
import com.itanik.keddit.commons.adapter.ViewType

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}