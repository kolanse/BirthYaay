package com.example.birthyaay

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.navigation.navigation.Navigator
import com.example.remote.CheckingHilt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity() : AppCompatActivity(), Navigator {

    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_fragment_container) as NavHostFragment
        navController = findNavController(R.id.activity_main_fragment_container)
    }

    override fun navigateTo(destination: Int) {
        navController.navigate(destination)
    }

    override fun navigateTo(uri: Uri) {
        val navDeepLinkRequest = NavDeepLinkRequest.Builder
            .fromUri(uri)
            .build()
        navController.navigate(navDeepLinkRequest)
    }

    override fun graphSpecificNavigation(graphResId: Int) {
        val navHostFragment: NavHostFragment = navHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(graphResId)
        navController.graph.addAll(graph)
    }

}
