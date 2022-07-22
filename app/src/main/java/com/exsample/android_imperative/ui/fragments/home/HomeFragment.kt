package com.exsample.android_imperative.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exsample.android_imperative.R
import com.exsample.android_imperative.adapter.TvShowAdapter
import com.exsample.android_imperative.databinding.FragmentHomeBinding
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.ui.fragments.BaseFragment
import com.exsample.android_imperative.utils.Logger
import com.exsample.android_imperative.utils.TvShowAdapterClickListner

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TvShowAdapter
    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSharedElementEnterTransition(TransitionInflater.from(getContext())
            .inflateTransition(android.R.transition.move));
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        initObserves()
        val glm = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = glm
        refreshAdapter(ArrayList())

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (glm.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                    val nextPage = viewModel.tvShowPopular.value!!.page + 1
                    Logger.d(TAG, nextPage.toString())
                    viewModel.apiTVShowPopular(nextPage)
                }
            }
        })
        binding.bFab.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }

        viewModel.apiTVShowPopular(1)
    }

    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowsFromApi.observe(requireActivity(), {
            Logger.d(TAG, it.size.toString())
            adapter.setNewShows(it)
        })
        viewModel.errorMessage.observe(requireActivity(), {
            Logger.d(TAG, it.toString())
        })
        viewModel.isLoading.observe(requireActivity(), Observer {
            Logger.d(TAG, it.toString())
            if (viewModel.isLoading.value == true) {
                binding.prLoading.visibility = View.VISIBLE
            } else {
                binding.prLoading.visibility = View.GONE
            }
        })

        // Room Related
        viewModel.tvShowsFromDB.observe(requireActivity(), {
            Logger.d(TAG, it!!.size.toString())
        })
    }

    private fun refreshAdapter(items: ArrayList<TVShow>) {
        adapter = TvShowAdapter(items, object : TvShowAdapterClickListner{
            override fun onItemClick(tvShow: TVShow, imageView: ImageView) {
                //Save TVShow to Room
                viewModel.insertTVShowToDB(tvShow)
                // Call Details Activity
                callDetailsActivity(tvShow, imageView)
            }

        })
        binding.recyclerView.adapter = adapter
    }

    fun callDetailsActivity(tvShow: TVShow, sharedImageView: ImageView) {
        Log.d(TAG, "callDetailsActivity: ${tvShow.id}")
        findNavController().navigate(R.id.action_navigation_home_to_detailsFragment,
        bundleOf("show_id" to  tvShow.id,
            "show_img" to tvShow.image_thumbnail_path,
            "show_name" to tvShow.name,
            "show_network" to tvShow.network))
    }
}