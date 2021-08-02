package com.interview.casestudy.model

import androidx.recyclerview.widget.RecyclerView
import com.interview.casestudy.ui.home.SingleRecyclerAdapter

data class HorizontalList(
    var adapter: SingleRecyclerAdapter<*, *>? = null,
    val pool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
    var itemDecoration: RecyclerView.ItemDecoration? = null
)
