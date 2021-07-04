package com.example.birthyaay.util

import android.net.Uri

const val ZERO_INDEX = 0
const val PICK_IMAGE_REQUEST_CODE = 101
const val PERMISSION_NAME = "external storage"
var imageUri: Uri? = null

enum class ContentType {
    GIFT, INTEREST, NEUTRAL
}
