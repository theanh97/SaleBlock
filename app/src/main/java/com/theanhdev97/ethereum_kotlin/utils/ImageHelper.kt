package com.theanhdev97.ethereum_kotlin.utils

import android.content.Context
import android.widget.ImageView

import com.bumptech.glide.Glide

/**
 * Created by DELL on 17/01/2018.
 */

object ImageHelper {
    fun loadImage(context: Context, imageView: ImageView, url: String, placeHolderID: Int) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(placeHolderID)
                .into(imageView)
    }
}
