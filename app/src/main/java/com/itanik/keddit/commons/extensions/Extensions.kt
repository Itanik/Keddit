package com.itanik.keddit.commons.extensions

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.itanik.keddit.R
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Picasso.get().load(imageUrl).into(this)
    } else {
        this.setImageResource(R.drawable.ic_launcher_background)
    }
}