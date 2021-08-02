package com.interview.casestudy.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.*

fun <T> LifecycleOwner.eventObserve(liveData: LiveData<Event<T>>?, observer: (T) -> Unit) {
    liveData?.observe(this, EventObserver(observer))
}

fun String.formattedStartDate(): String {
    val date = Date(toLong() * 1000)
    return date.formatToViewTime("dd/MM/yyyy, E")
}

fun Date.formatToViewTime(customFormat: String = "dd MMMM yyyy"): String {
    val sdf = SimpleDateFormat(customFormat, Locale.getDefault())
    Calendar.getInstance().time
    return sdf.format(this)
}