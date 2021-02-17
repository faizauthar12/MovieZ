package io.faizauthar12.moviez.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.databinding.ActivityDetailShowBinding
import io.faizauthar12.moviez.databinding.ContentDetailShowBinding

class DetailShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_DETAIL = "extra_data_detail"
    }

    private lateinit var detailContentBinding: ContentDetailShowBinding
    private var data: ShowEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowBinding.detailContent

        setContentView(activityDetailShowBinding.root)

        setSupportActionBar(activityDetailShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[DetailShowViewModel::class.java]
         */

        // Visibility changes before populated
        activityDetailShowBinding.progressBar.visibility = View.VISIBLE
        activityDetailShowBinding.content.visibility = View.INVISIBLE

        val extras = intent.extras
        if (extras != null ){
            data = intent.getParcelableExtra(EXTRA_DATA_DETAIL)
            data?.let { populateDetail(it) }

            // Visibility changes after succesfully populated
            activityDetailShowBinding.progressBar.visibility = View.GONE
            activityDetailShowBinding.content.visibility = View.VISIBLE
        } else {
            val toast = Toast.makeText(
                    applicationContext,
                    "Maaf, item ini tidak memiliki halaman detail",
                    Toast.LENGTH_LONG
            )
            toast.show()
            finish()
        }
    }

    private fun populateDetail(showEntity: ShowEntity) {
        detailContentBinding.tvTitle.text = showEntity.title
        detailContentBinding.tvDescription.text = showEntity.description
        detailContentBinding.tvReleaseYear.text = showEntity.releaseYear

        supportActionBar?.title = showEntity.title

        Glide.with(this)
                .load(showEntity.imagePath)
                .transform(RoundedCorners(10))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }
}