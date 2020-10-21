package com.png.tutorialproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.bottom_nav

class MainActivity : AppCompatActivity() {

    companion object {
        val topLevelDestinations = setOf(
            R.id.flow_fragment,
            R.id.room_fragment,
            R.id.livedata_fragment
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.mainNavigationFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav.setupWithNavController(navController)
        bottom_nav.setOnNavigationItemReselectedListener {  }
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.mainNavigationFragment).navigateUp()
}