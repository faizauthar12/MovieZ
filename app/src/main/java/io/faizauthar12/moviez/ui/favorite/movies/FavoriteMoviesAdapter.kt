package io.faizauthar12.moviez.ui.favorite.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.BuildConfig
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.databinding.ItemsMoviesBinding

class FavoriteMoviesAdapter : RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(){

    private val listFavoriteMovie = ArrayList<ShowEntity>()

    var onItemClickCallback: FavoriteMoviesAdapter.OnItemClickCallback? = null

    fun setFavoriteMovies(favoriteMovies: List<ShowEntity>?) {
        if (favoriteMovies.isNullOrEmpty()) return
        this.listFavoriteMovie.clear()
        this.listFavoriteMovie.addAll(favoriteMovies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FavoriteMoviesViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FavoriteMoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val favoriteMovie = listFavoriteMovie[position]
        holder.bind(favoriteMovie) {
            onItemClickCallback?.onItemClicked(favoriteMovie)
        }
    }

    override fun getItemCount(): Int = listFavoriteMovie.size

    class FavoriteMoviesViewHolder(private val binding: ItemsMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: ShowEntity, itemClicked: () -> Unit) {
            with(binding) {
                tvMovieTitle.text = favoriteMovie.Title
                tvMovieRelease.text = favoriteMovie.release
                tvOverview.text = favoriteMovie.overview
                Glide.with(itemView.context)
                        .load(BuildConfig.BaseImageUrl + favoriteMovie.posterPath)
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