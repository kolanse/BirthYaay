package com.example.navigation.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    val title: String,
    val isClicked: Boolean,
    val contentType: ContentType,
    val drawableRes: Int = 0,
    val viewType: Int = 0
): Parcelable

enum class ContentType {
    GIFT, INTEREST, PICTURE, NEUTRAL
}