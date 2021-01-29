package io.faizauthar12.moviez.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.faizauthar12.moviez.R

class DetailShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_show)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}