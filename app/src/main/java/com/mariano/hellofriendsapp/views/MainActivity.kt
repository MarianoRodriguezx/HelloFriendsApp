package com.mariano.hellofriendsapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.mariano.hellofriendsapp.NavAppDirections
import com.mariano.hellofriendsapp.R
import com.mariano.hellofriendsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)


        val navHostFragment: NavHostFragment = binding.fragment.getFragment()
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.home -> {
                    navController.navigate(NavAppDirections.globalHome())
                }

                R.id.contacts -> {
                    navController.navigate(NavAppDirections.globalContacts())
                }

                R.id.search -> {
                    navController.navigate(NavAppDirections.globalSearch())
                }

                R.id.profile -> {
                    navController.navigate(NavAppDirections.globalProfile())
                }
            }
            return@setOnItemSelectedListener true
        }

        setContentView(binding.root)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }
}