package io.faizauthar12.moviez.ui.favorite.series

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.databinding.FragmentFavoriteSeriesBinding
import io.faizauthar12.moviez.factory.ViewModelFactory
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.ui.series.SeriesAdapter

class FavoriteSeriesFragment : Fragment() {

    // Binding support
    private lateinit var fragmentFavoriteSeriesBinding: FragmentFavoriteSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding support
        fragmentFavoriteSeriesBinding = FragmentFavoriteSeriesBinding.inflate(layoutInflater, container,false)
        return fragmentFavoriteSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[FavoriteSeriesViewModel::class.java]

            val seriesAdapter = SeriesAdapter()

            fragmentFavoriteSeriesBinding.progressBar.visibility = View.VISIBLE

            viewModel.getAllFavoriteSeries().observe(viewLifecycleOwner, { data ->
                seriesAdapter.submitList(data)
                seriesAdapter.notifyDataSetChanged()
            })

            with(fragmentFavoriteSeriesBinding.rvFavoriteSerie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }

            fragmentFavoriteSeriesBinding.progressBar.visibility = View.GONE

            seriesAdapter.onItemClickCallback = object : SeriesAdapter.OnItemClickCallback{
                override fun onItemClicked(data: ShowEntity) {
                    Intent(activity, DetailShowActivity::class.java).apply {
                        putExtra(DetailShowActivity.EXTRA_DATA_DETAIL,data)
                        putExtra(DetailShowActivity.EXTRA_IS_FAVORITE, true)
                        startActivity(this)
                    }
                }
            }
        }
    }
}