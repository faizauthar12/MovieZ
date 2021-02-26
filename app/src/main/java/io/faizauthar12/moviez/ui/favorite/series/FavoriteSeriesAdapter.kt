package io.faizauthar12.moviez.ui.favorite.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.BuildConfig
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.databinding.ItemsSeriesBinding

class FavoriteSeriesAdapter : RecyclerView.Adapter<FavoriteSeriesAdapter.FavoriteSeriesViewHolder>() {

    private val listFavoriteSerie = ArrayList<ShowEntity>()

    var onItemClickCallback: FavoriteSeriesAdapter.OnItemClickCallback? = null

    fun setFavoriteSeries(favoriteSeries: List<ShowEntity>?) {
        if (favoriteSeries.isNullOrEmpty()) return
        this.listFavoriteSerie.clear()
        this.listFavoriteSerie.addAll(favoriteSeries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteSeriesViewHolder {
        val itemsSeriesBinding = ItemsSeriesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FavoriteSeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: FavoriteSeriesViewHolder, position: Int) {
        val favoriteSerie = listFavoriteSerie[position]
        holder.bind(favoriteSerie) {
            onItemClickCallback?.onItemClicked(favoriteSerie)
        }
    }

    override fun getItemCount(): Int = listFavoriteSerie.size

    class FavoriteSeriesViewHolder(private val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteSerie: ShowEntity, itemClicked: () -> Unit ) {
            with(binding) {
                tvSeriesTitle.text = favoriteSerie.Title
                tvSeriesRelease.text = favoriteSerie.release
                tvOverview.text = favoriteSerie.overview
                Glide.with(itemView.context)
                        .load(BuildConfig.BaseImageUrl + favoriteSerie.posterPath)
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