package io.faizauthar12.moviez.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.BASE_IMAGE_URL
import io.faizauthar12.moviez.databinding.ItemsMoviesBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<ShowEntity>()

    var onItemClickCallback: OnItemClickCallback? = null

    fun setMovies(movies: List<ShowEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie) {
            onItemClickCallback?.onItemClicked(movie)
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(private val binding: ItemsMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ShowEntity, itemClicked: () -> Unit) {
            with(binding) {
                tvMovieTitle.text = movie.Title
                tvMovieRelease.text = movie.release
                tvOverview.text = movie.overview
                Glide.with(itemView.context)
                        .load(BASE_IMAGE_URL + movie.posterPath)
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