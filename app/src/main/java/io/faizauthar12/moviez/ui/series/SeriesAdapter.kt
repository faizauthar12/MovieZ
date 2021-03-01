package io.faizauthar12.moviez.ui.series

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
import io.faizauthar12.moviez.databinding.ItemsSeriesBinding
import io.faizauthar12.moviez.utils.EspressoIdlingResource

class SeriesAdapter : PagedListAdapter<ShowEntity, SeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemsSeriesBinding = ItemsSeriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SeriesViewHolder(itemsSeriesBinding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position)
        series?.let {
            holder.bind(series) {
                onItemClickCallback?.onItemClicked(series)
            }
        }
    }

    class SeriesViewHolder(private val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: ShowEntity, itemClicked: () -> Unit){
            with(binding){
                tvSeriesTitle.text = series.title
                tvSeriesRelease.text = series.releaseDate
                tvOverview.text = series.overview
                Glide.with(itemView.context)
                        .load(BaseImageUrl + series.posterPath)
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