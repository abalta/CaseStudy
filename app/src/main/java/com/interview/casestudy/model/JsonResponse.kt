package com.interview.casestudy.model

import com.google.gson.annotations.SerializedName
import com.interview.casestudy.util.formattedStartDate

data class BlobResponse(
    @SerializedName("isBannerEnabled")
    val isBannerEnabled: Boolean?,
    @SerializedName("meditations")
    val meditations: List<Meditation>?,
    @SerializedName("stories")
    val stories: List<Story>?
)

data class Meditation(
    @SerializedName("content")
    val content: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?
) {
    fun mapToDetailItem() = DetailItem(title, subtitle, content, releaseDate?.formattedStartDate())
}

data class Story(
    @SerializedName("category")
    val category: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("image")
    val image: ImageX?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("text")
    val text: String?
) {
    fun mapToDetailItem() = DetailItem(name, category, text, date?.formattedStartDate())
}

data class Image(
    @SerializedName("large")
    val large: String?,
    @SerializedName("small")
    val small: String?
)

data class ImageX(
    @SerializedName("large")
    val large: String?,
    @SerializedName("small")
    val small: String?
)

data class DetailItem(
    val title: String?,
    val subTitle: String?,
    val content: String?,
    val date: String?
)
