package io.faizauthar12.moviez.ui.series

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.databinding.FragmentSeriesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.factory.ViewModelFactory
import io.faizauthar12.moviez.util.Status

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

            viewModel.getSeries().observe(viewLifecycleOwner, {
                seriesAdapter.submitList(it)
            })

            viewModel.getSeriesNetworkState().observe(viewLifecycleOwner, { networkState ->
                when(networkState.status) {
                    Status.RUNNING -> {
                        if (viewModel.seriesListIsEmpty()) {
                            fragmentSeriesBinding.progressBar.visibility = View.VISIBLE
                        }
                    }
                    Status.SUCCESS -> {
                        fragmentSeriesBinding.progressBar.visibility = View.GONE
                    }
                    Status.FAIL -> {
                        fragmentSeriesBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
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