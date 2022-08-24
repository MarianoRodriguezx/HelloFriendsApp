package com.mariano.hellofriendsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mariano.hellofriendsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.home -> {
                    Toast.makeText(this, "ENTRO A EL HOME", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}