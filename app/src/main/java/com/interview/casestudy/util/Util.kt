package com.interview.casestudy.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imgUrl"])
fun ImageView.loadImageFromUrl(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter(value = ["itemDecoration"])
fun setItemDecoration(view: RecyclerView, itemDecoration: RecyclerView.ItemDecoration) {
    view.removeItemDecoration(itemDecoration)
    view.addItemDecoration(itemDecoration)
}