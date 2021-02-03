package io.faizauthar12.moviez.ui.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.databinding.FragmentSeriesBinding

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
            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[SeriesViewModel::class.java]
            val series = viewModel.getSeries()

            val seriesAdapter = SeriesAdapter()
            seriesAdapter.setSeries(series)

            with(fragmentSeriesBinding.rvSeries){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }
}