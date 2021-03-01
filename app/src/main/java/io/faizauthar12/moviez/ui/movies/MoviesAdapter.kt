package io.faizauthar12.moviez.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.BuildConfig.BaseImageUrl
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.databinding.ItemsMoviesBinding
import io.faizauthar12.moviez.utils.EspressoIdlingResource

class MoviesAdapter : PagedListAdapter<ShowEntity, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShowEntity>() {
            override fun areItemsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ShowEntity, newItem: ShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(movie) {
                onItemClickCallback?.onItemClicked(movie)
            }
        }
    }

    class MovieViewHolder(private val binding: ItemsMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ShowEntity, itemClicked: () -> Unit) {
            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieRelease.text = movie.releaseDate
                tvOverview.text = movie.overview
                Glide.with(itemView.context)
                        .load(BaseImageUrl + movie.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
                itemView.setOnClickListener { itemClicked.invoke() }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ShowEntity)
    }
}