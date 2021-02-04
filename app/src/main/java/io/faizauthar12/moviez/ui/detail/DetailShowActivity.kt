package io.faizauthar12.moviez.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.databinding.ActivityDetailShowBinding
import io.faizauthar12.moviez.databinding.ContentDetailShowBinding

class DetailShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var detailContentBinding: ContentDetailShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowBinding = ActivityDetailShowBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowBinding.detailContent

        setContentView(activityDetailShowBinding.root)

        setSupportActionBar(activityDetailShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null ){
            val showsId = extras.getString(EXTRA_ID)
            if(showsId != null){
                viewModel.setSelectedShow(showsId)

                when(extras.getInt(EXTRA_CATEGORY)){
                    /*
                        1 = Movies
                        2 = Series
                     */
                    1 -> populateDetail(viewModel.getMovie())
                    2 -> populateDetail(viewModel.getSeries())
                }
            }
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