package com.example.navigation.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Celebrant(
    var name: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val daysLeftToBirthday: String? = null,
    val interests: List<Content>? = null,
    val gifts: List<Content>? = null,
    val image: List<String>? = null,
    val note: String? = null,
    var viewType: Int = 0
): Parcelable