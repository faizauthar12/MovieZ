package io.faizauthar12.moviez.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    // Binding support
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Binding support
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container,false)
        return fragmentMoviesBinding.root
    }
}