package com.spitaliere.marvelcharacters.presentation.platform.extension

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/

fun RecyclerView.assemble(
    context: Context,
    adapter: RecyclerView.Adapter<*>
){
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
    this.hasFixedSize()
}

fun ImageView.setImage(
    url: String,
    onError: (() -> Unit) = {},
    onSuccess: (() -> Unit) = {}
) {
    Picasso.get()
        .load(url)
        .resize(640, 360)
        .centerCrop()
        .into(this, object : Callback{
            override fun onSuccess() {
                onSuccess()
            }

            override fun onError(e: Exception?) {
                onError()
            }
        })
}