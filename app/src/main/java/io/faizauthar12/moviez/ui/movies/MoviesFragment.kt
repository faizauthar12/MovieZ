package io.faizauthar12.moviez.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.faizauthar12.moviez.data.source.local.entity.ShowEntity
import io.faizauthar12.moviez.databinding.FragmentMoviesBinding
import io.faizauthar12.moviez.ui.detail.DetailShowActivity
import io.faizauthar12.moviez.ui.detail.DetailShowActivity.Companion.EXTRA_DATA_DETAIL
import io.faizauthar12.moviez.viewmodel.ViewModelFactory

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
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this,factory)[MoviesViewModel::class.java]

            val moviesAdapter = MoviesAdapter()

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                moviesAdapter.setMovies(movies)
                moviesAdapter.notifyDataSetChanged()
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