package com.example.birthyaay.models

import com.example.birthyaay.R
import com.example.birthyaay.util.ContentType

data class GiftOrInterestContent(
    val title: String,
    val isClicked: Boolean,
    val drawableRes: Int,
    val contentType: ContentType,
    val viewType: Int = 0
)
