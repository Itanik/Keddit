package com.itanik.keddit.commons

import android.os.Parcelable
import com.itanik.keddit.commons.adapter.AdapterConstants
import com.itanik.keddit.commons.adapter.ViewType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
) : Parcelable

@Parcelize
data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String?
) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.NEWS

}