package com.spitaliere.marvelcharacters.presentation.platform.extension

import android.animation.Animator
import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
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

fun ImageView.animateAlpha(){
    val a = AlphaAnimation(0.00f, 1.00f)
    a.duration = 2000
    this.startAnimation(a)
}

fun ImageView.animateMove(y:Float, runnable: Runnable){
    this.animate().x(100f).y(y).apply {
        duration = 5000
        withEndAction( runnable )
    }
}

fun LottieAnimationView.setListener(runnableEnd: Runnable){
    this.addAnimatorListener(object : Animator.AnimatorListener{
        override fun onAnimationRepeat(p0: Animator?) {
            this@setListener.pauseAnimation()
            runnableEnd.run()
        }
        override fun onAnimationEnd(p0: Animator?) = Unit
        override fun onAnimationCancel(p0: Animator?)= Unit
        override fun onAnimationStart(p0: Animator?) = Unit
    })
}