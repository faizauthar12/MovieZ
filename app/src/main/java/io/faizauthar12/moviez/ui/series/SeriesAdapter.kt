package io.faizauthar12.moviez.ui.series

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.data.ShowEntity
import io.faizauthar12.moviez.databinding.ItemsSeriesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>(){

    private val listSeries = ArrayList<ShowEntity>()

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
        holder.bind(series)
    }

    override fun getItemCount(): Int = listSeries.size

    class SeriesViewHolder(private val binding: ItemsSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(series: ShowEntity){
            with(binding){
                tvSeriesTitle.text = series.title
                tvSeriesRelease.text = series.releaseYear
                tvOverview.text = series.description
                Glide.with(itemView.context)
                        .load(series.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailShowActivity::class.java)
                    intent.putExtra(DetailShowActivity.EXTRA_CATEGORY,2)
                    intent.putExtra(DetailShowActivity.EXTRA_ID, series.showsId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}