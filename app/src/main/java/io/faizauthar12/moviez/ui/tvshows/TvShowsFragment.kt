package io.faizauthar12.moviez.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.faizauthar12.moviez.R
import io.faizauthar12.moviez.databinding.FragmentTvShowsBinding

class TvShowsFragment : Fragment() {

    // Binding support
    private lateinit var fragmentTvShowsBinding: FragmentTvShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Binding support
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }
}