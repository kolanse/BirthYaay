package com.example.birthyaay.util

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.birthyaay.databinding.FragmentHomeBinding

fun Fragment.isShowOrHideView(isShow: Boolean, binding: FragmentHomeBinding) {
    binding.apply {
        fragmentHomeGreetingLoveIv.isVisible = isShow
        fragmentHomeGreetingTitleTv.isVisible = isShow
        fragmentHomeSearchEt.isVisible = isShow
        fragmentHomeLoveToolbarIv.isVisible = !isShow
        fragmentHomeSearchToolbarIv.isVisible = !isShow
    }
}