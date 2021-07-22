package com.example.birthyaay.util

import android.net.Uri

const val ZERO_INDEX = 0
const val PICK_IMAGE_REQUEST_CODE = 101
const val WRITE_STORAGE_REQUEST_CODE = 10001
const val READ_STORAGE_REQUEST_CODE = 100001
const val SEND_SMS_REQUEST_CODE = 1001
const val PERMISSION_NAME = "external storage"
var REQUEST_CODE = 0

var imageUri: Uri? = null

enum class ContentType {
    GIFT, INTEREST, NEUTRAL
}
