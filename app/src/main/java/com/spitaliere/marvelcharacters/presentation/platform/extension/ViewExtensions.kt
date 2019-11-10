package com.spitaliere.marvelcharacters.presentation.platform.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spitaliere.marvelcharacters.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/

fun RecyclerView.assemble(
    context: Context,
    adapter: RecyclerView.Adapter<*>,
    horizontal: Boolean = false
){
    this.layoutManager = LinearLayoutManager(context, if (horizontal) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL, false )
    this.adapter = adapter
    this.hasFixedSize()
}

fun View.visible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ImageView.setImage(
    url: String,
    horizontal: Boolean = true,
    onError: (() -> Unit) = {},
    onSuccess: (() -> Unit) = {}
) {
    setPicasso(this, url, horizontal, onSuccess, onError)
}

fun CircleImageView.setImage(
    url: String,
    onError: (() -> Unit) = {},
    onSuccess: (() -> Unit) = {}
){
    setPicasso(this, url, false, onSuccess, onError)
}

private fun setPicasso(
    target: ImageView,
    url: String,
    horizontal : Boolean,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {}
) {
    val picasso = Picasso.get()
        .load(url)

    if (horizontal){
        picasso.resize(640, 360)
    } else {
        picasso.resize(360, 640)
    }

    picasso
        .centerCrop()
        .into(target, object : Callback {
            override fun onSuccess() {
                onSuccess()
            }

            override fun onError(e: Exception?) {
                onError()
            }
        })
}