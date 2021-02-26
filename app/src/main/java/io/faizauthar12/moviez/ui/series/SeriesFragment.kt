package io.faizauthar12.moviez.ui.series

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.databinding.FragmentSeriesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.viewmodel.ViewModelFactory

class SeriesFragment : Fragment() {

    // Binding support
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Binding support
        fragmentSeriesBinding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[SeriesViewModel::class.java]

            val seriesAdapter = SeriesAdapter()

            fragmentSeriesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getSeries().observe(viewLifecycleOwner, { series ->
                fragmentSeriesBinding.progressBar.visibility = View.GONE
                seriesAdapter.setSeries(series)
                seriesAdapter.notifyDataSetChanged()
            })

            with(fragmentSeriesBinding.rvSeries){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }

            seriesAdapter.onItemClickCallback = object : SeriesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ShowEntity) {
                    Intent(activity, DetailShowActivity::class.java).apply {
                        putExtra(DetailShowActivity.EXTRA_DATA_DETAIL,data)
                        startActivity(this)
                    }
                }
            }
        }
    }
}