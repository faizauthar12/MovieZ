package io.faizauthar12.moviez.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.faizauthar12.moviez.databinding.ActivityDetailShowBinding
import io.faizauthar12.moviez.databinding.ContentDetailShowBinding

class DetailShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOW = "extra_show"
    }

    private lateinit var detailContentBinding: ContentDetailShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowBinding.detailContent

        setContentView(activityDetailShowBinding.root)

        setSupportActionBar(activityDetailShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}