package io.faizauthar12.moviez.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.data.source.remote.request.ApiRequest.Companion.BASE_IMAGE_URL
import io.faizauthar12.moviez.databinding.ItemsSeriesBinding

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>(){

    private val listSeries = ArrayList<ShowEntity>()

    var onItemClickCallback: SeriesAdapter.OnItemClickCallback? = null

    fun setSeries(series: List<ShowEntity>?){
        if (series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemsSeriesBinding = ItemsSeriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = listSeries[position]
        holder.bind(series) {
            onItemClickCallback?.onItemClicked(series)
        }
    }

    override fun getItemCount(): Int = listSeries.size

    class SeriesViewHolder(private val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: ShowEntity, itemClicked: () -> Unit){
            with(binding){
                tvSeriesTitle.text = series.Title
                tvSeriesRelease.text = series.release
                tvOverview.text = series.overview
                Glide.with(itemView.context)
                        .load(BASE_IMAGE_URL + series.posterPath)
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