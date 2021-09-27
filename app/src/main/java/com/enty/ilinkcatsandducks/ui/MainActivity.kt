package com.enty.ilinkcatsandducks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.enty.ilinkcatsandducks.R
import com.enty.ilinkcatsandducks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            val navController = findNavController(R.id.fragmentContainerView)
            when(it.itemId){
                R.id.cats_ducks -> navController.navigate(R.id.catOrDuckFragment)
                R.id.favorite -> navController.navigate(R.id.favoriteFragment)
            }
            true
        }
        binding.bottomNav.setOnItemReselectedListener {

        }

    }
}