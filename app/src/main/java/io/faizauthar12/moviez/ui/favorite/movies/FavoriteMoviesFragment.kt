package io.faizauthar12.moviez.ui.favorite.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.databinding.FragmentFavoriteMoviesBinding
import io.faizauthar12.moviez.factory.ViewModelFactory
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.ui.detail.DetailShowActivity.Companion.EXTRA_IS_FAVORITE
import io.faizauthar12.moviez.ui.movies.MoviesAdapter

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
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

            fragmentFavoriteMoviesFragmentBinding.progressBar.visibility = View.VISIBLE

            viewModel.getAllFavoriteMovies().observe(viewLifecycleOwner, { data ->
                moviesAdapter.submitList(data)
                moviesAdapter.notifyDataSetChanged()
            })

            with(fragmentFavoriteMoviesFragmentBinding.rvFavoriteMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }

            fragmentFavoriteMoviesFragmentBinding.progressBar.visibility = View.GONE

            moviesAdapter.onItemClickCallback = object : MoviesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ShowEntity) {
                    Intent(activity, DetailShowActivity::class.java).apply {
                        putExtra(DetailShowActivity.EXTRA_DATA_DETAIL,data)
                        putExtra(EXTRA_IS_FAVORITE, true)
                        startActivity(this)
                    }
                }
            }
        }
    }
}