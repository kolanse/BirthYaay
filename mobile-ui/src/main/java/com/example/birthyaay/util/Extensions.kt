package com.example.birthyaay.util

import android.app.Activity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.example.birthyaay.R
import com.example.birthyaay.databinding.FragmentAddCelebrantBinding
import com.example.birthyaay.databinding.FragmentHomeBinding
import com.example.birthyaay.databinding.FragmentPeopleBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.isShowOrHideView(isShow: Boolean, binding: ViewBinding) {

    when (binding) {
        is FragmentHomeBinding -> {
            binding.apply {
                fragmentHomeGreetingLoveIv.isVisible = isShow
                fragmentHomeGreetingTitleTv.isVisible = isShow
                fragmentHomeSearchEt.isVisible = isShow
                fragmentHomeLoveToolbarIv.isVisible = !isShow
                fragmentHomeSearchToolbarIv.isVisible = !isShow
            }
        }
        is FragmentPeopleBinding -> {
            binding.apply {
                fragmentPeopleGreetingLoveIv.isVisible = isShow
                fragmentPeopleGreetingTitleTv.isVisible = isShow
                fragmentPeopleSearchEt.isVisible = isShow
                fragmentPeopleLoveToolbarIv.isVisible = !isShow
                fragmentPeopleSearchToolbarIv.isVisible = !isShow
            }
        }
        is FragmentAddCelebrantBinding -> {
            binding.apply {
                fragmentAddCelebrantBalloonIv.isVisible = isShow
                fragmentAddCelebrantTitleTv.isVisible = isShow
                //fragmentAddCelebrantToolbar.isVisible = !isShow
                fragmentAddCelebrantToolbarIv.isVisible = !isShow
            }
        }
    }
}

fun Fragment.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)
    return navBuilder
}

fun Activity.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.fade_in).setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in).setPopExitAnim(R.anim.fade_out)
    return navBuilder
}

fun BottomNavigationView.checkMenuItem(destinationId: Int) {
    for (i in 0 until menu.size()) {
        val item: MenuItem = menu.getItem(i)
        item.isChecked = false
    }
    menu.findItem(destinationId)?.isChecked = true
}
