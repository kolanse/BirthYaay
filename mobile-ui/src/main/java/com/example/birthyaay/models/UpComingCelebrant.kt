package com.example.birthyaay.models


data class UpComingCelebrant(
    var celebrantImage: String? = null,
    var celebrantName: String? = null,
    var phoneNumber: String? = null,
    var note: String? = null,
    var celebrantDateOfBirth: String? = null,
    var daysLeftToBirthday: String? = null,
    var viewType: Int = 0
)