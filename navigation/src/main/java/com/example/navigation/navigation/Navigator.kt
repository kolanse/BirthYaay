package com.example.navigation.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

interface Navigator {
    var navHostFragment: NavHostFragment
    var navController: NavController

    fun navigateTo(destination: Int)
    fun navigateTo(uri: Uri)

    fun navigateTo(destination: Int, graphResId: Int) {
        val navHostFragment = navHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(graphResId)
        navHostFragment.navController.graph = graph
        navigateTo(destination)
    }

    fun navigateTo(uri: Uri, graphResId: Int) {
        val navHostFragment = navHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(graphResId)
        navHostFragment.navController.graph = graph
        navigateTo(uri)
    }

    fun graphSpecificNavigation(graphResId: Int)
}