package io.faizauthar12.moviez.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.BuildConfig.BaseImageUrl
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.databinding.ActivityDetailShowBinding
import io.faizauthar12.moviez.databinding.ContentDetailShowBinding
import io.faizauthar12.moviez.factory.ViewModelFactory

class DetailShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_DETAIL = "extra_data_detail"
        const val EXTRA_IS_FAVORITE = "is_favorite"
    }

    private lateinit var detailContentBinding: ContentDetailShowBinding
    private lateinit var activityDetailShowBinding: ActivityDetailShowBinding
    private var data: ShowEntity? = null

    private var statusFavorite = false
    private var isFavorite = false
    private lateinit var viewModel: DetailShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailShowBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowBinding.detailContent

        setContentView(activityDetailShowBinding.root)

        setSupportActionBar(activityDetailShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Visibility changes before populated
        activityDetailShowBinding.progressBar.visibility = View.VISIBLE
        activityDetailShowBinding.content.visibility = View.INVISIBLE

        // check isfavorite
        isFavorite = intent.getBooleanExtra(EXTRA_IS_FAVORITE,false)
        if (isFavorite) {
            statusFavorite = !statusFavorite
            setStatusFavorite(statusFavorite)
        }

        // viewmodel
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null ){
            data = intent.getParcelableExtra(EXTRA_DATA_DETAIL)
            data?.let { populateDetail(it) }

            // Visibility changes after succesfully populated
            activityDetailShowBinding.progressBar.visibility = View.GONE
            activityDetailShowBinding.content.visibility = View.VISIBLE

            setUserFavorite()
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
        detailContentBinding.tvDescription.text = showEntity.overview
        detailContentBinding.tvReleaseYear.text = showEntity.releaseDate

        supportActionBar?.title = showEntity.title

        Glide.with(this)
                .load(BaseImageUrl + showEntity.posterPath)
                .transform(RoundedCorners(10))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }

    private fun setUserFavorite() {
        setStatusFavorite(statusFavorite)
        activityDetailShowBinding.fab.setOnClickListener {
            if (statusFavorite) {
                data?.let {
                    viewModel.deleteFavorite(it)
                    isFavorite = false
                }
            } else {
                data?.let {
                    viewModel.insertFavorite(it)
                }
            }
            statusFavorite = !statusFavorite
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if(statusFavorite){
            activityDetailShowBinding.fab.setImageResource(R.drawable.ic_bx_heart_filled)
        }else{
            activityDetailShowBinding.fab.setImageResource(R.drawable.ic_bx_heart_unfill)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        setUserFavorite()
    }
}