package io.faizauthar12.moviez.ui.movies

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
import io.faizauthar12.moviez.databinding.FragmentMoviesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.ui.detail.DetailShowActivity.Companion.EXTRA_DATA_DETAIL
import io.faizauthar12.moviez.factory.ViewModelFactory
import io.faizauthar12.moviez.util.Status

class MoviesFragment : Fragment() {

    // Binding support
    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Binding support
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container,false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE

            viewModel.getMovies().observe(viewLifecycleOwner, {
                moviesAdapter.submitList(it)
            })

            viewModel.getMoviesNetworkState().observe(viewLifecycleOwner, { networkState ->
                when(networkState.status) {
                    Status.RUNNING -> {
                        if (viewModel.moviesListIsEmpty()) {
                            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
                        }
                    }
                    Status.SUCCESS -> {
                        fragmentMoviesBinding.progressBar.visibility = View.GONE
                    }
                    Status.FAIL -> {
                        fragmentMoviesBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(fragmentMoviesBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }

            moviesAdapter.onItemClickCallback = object : MoviesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ShowEntity) {
                    Intent(activity, DetailShowActivity::class.java).apply {
                        putExtra(EXTRA_DATA_DETAIL,data)
                        startActivity(this)
                    }
                }
            }
        }
    }
}