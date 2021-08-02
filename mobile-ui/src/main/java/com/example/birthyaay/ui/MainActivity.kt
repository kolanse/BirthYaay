package com.example.birthyaay.ui


import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.birthyaay.R
import com.example.birthyaay.databinding.ActivityMainBinding
import com.example.birthyaay.util.checkMenuItem
import com.example.navigation.navigation.Navigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity() : AppCompatActivity(), Navigator {

    override lateinit var navHostFragment: NavHostFragment
    override lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_fragment_container) as NavHostFragment
        navController = findNavController(R.id.activity_main_fragment_container)

        binding.apply {
            activityMainBottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.activityMainBottomNavigationView.isVisible = true
            if (destination.id == R.id.homeFragment) {
                binding.activityMainBottomNavigationView.checkMenuItem(R.id.homeFragment)
            } else if (destination.id == R.id.celebrantDetailsFragment) {
                binding.activityMainBottomNavigationView.isVisible = false
            }
        }

    }

    override fun onResume() {
        super.onResume()

    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener {

            val fragment = when (it.itemId) {
                R.id.peopleFragment -> {
                    R.id.peopleFragment
                }
                R.id.exploreGiftFragment -> {
                    R.id.exploreGiftFragment
                }
                R.id.homeFragment -> {
                    R.id.homeFragment
                }
                else -> {
                    R.id.homeFragment
                }
            }

            if (fragment != 0) {
                navController.navigate(fragment)
            }

            true
        }


    private fun selectActiveIcon() {

        binding.apply {
            val currentDestination = navController.currentDestination?.id

            if (currentDestination == R.id.peopleFragment) {
                activityMainBottomNavigationView.checkMenuItem(R.id.peopleFragment)
            } else if (currentDestination == R.id.exploreGiftFragment) {
                activityMainBottomNavigationView.checkMenuItem(R.id.exploreGiftFragment)
            }
        }
    }

    override fun onBackPressed() {
        val currentDestination = navController.currentDestination?.id

        if (currentDestination == R.id.homeFragment) {
            finish()
        } else {
//            navigateTo(R.id.homeFragment)
//            binding.activityMainBottomNavigationView.isVisible = true
            navController.popBackStack()
        }

         selectActiveIcon()
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
