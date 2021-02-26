package io.faizauthar12.moviez.ui.favorite.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.FragmentFavoriteMoviesBinding

class FavoriteMoviesFragment : Fragment() {

    // Binding support
    private lateinit var fragmentFavoriteMoviesFragmentBinding: FragmentFavoriteMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding support
        fragmentFavoriteMoviesFragmentBinding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMoviesFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteMoviesAdapter = FavoriteMoviesAdapter()

            fragmentFavoriteMoviesFragmentBinding.progressBar.visibility = View.VISIBLE
        }
    }
}