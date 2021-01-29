package io.faizauthar12.moviez.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
    }
}