package io.faizauthar12.moviez.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.databinding.ItemsMoviesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<ShowEntity>()

    fun setMovies(movies: List<ShowEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding = ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(private val binding: ItemsMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ShowEntity) {
            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieRelease.text = movie.releaseYear
                tvOverview.text = movie.description
                Glide.with(itemView.context)
                        .load(movie.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.EXTRA_CATEGORY,1)
                    intent.putExtra(DetailShowActivity.EXTRA_ID, movie.showsId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}