package io.faizauthar12.moviez.ui.favorite.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.FragmentFavoriteMoviesBinding
import io.faizauthar12.moviez.databinding.FragmentFavoriteSeriesBinding

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

            fragmentFavoriteSeriesBinding.progressBar.visibility = View.VISIBLE
        }
    }
}