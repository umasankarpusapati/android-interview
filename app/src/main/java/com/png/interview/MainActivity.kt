package com.png.interview

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.png.interview.ui.InjectedActivity

class MainActivity : InjectedActivity() {

    companion object {
        val topLevelDestinations = setOf(
            R.id.heroes_fragment,
            R.id.maps_fragment,
            R.id.about_fragment
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {

    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.mainNavigationFragment).navigateUp()
}