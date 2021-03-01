package io.faizauthar12.moviez.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.faizauthar12.moviez.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    // Binding support
    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding support
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container,false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            // Initialize TabLayout with ViewPager
            val sectionPagerAdapter = SectionPagerAdapter(this, childFragmentManager)
            fragmentFavoriteBinding.viewPager.adapter = sectionPagerAdapter
            fragmentFavoriteBinding.tabsFavorite.setupWithViewPager(fragmentFavoriteBinding.viewPager)
        }
    }
}