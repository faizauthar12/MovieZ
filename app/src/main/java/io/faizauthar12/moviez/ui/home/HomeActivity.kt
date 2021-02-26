package io.faizauthar12.moviez.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.ActivityHomeBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        setupBottomNavigation(activityHomeBinding)

        supportActionBar?.elevation = 0f
    }

    private fun setupBottomNavigation(activityHomeBinding: ActivityHomeBinding) {
        val navView: BottomNavigationView = activityHomeBinding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.seriesFragment, R.id.moviesFragment, R.id.favoriteFragment
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}