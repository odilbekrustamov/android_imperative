package com.exsample.android_imperative.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.exsample.android_imperative.R
import com.exsample.android_imperative.adapter.TVShortAdapter
import com.exsample.android_imperative.databinding.FragmentDetailsBinding
import com.exsample.android_imperative.utils.Logger
import com.exsample.android_imperative.viewmodel.DetailViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val TAG = "DetailsFragment"
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        initViews()
    }

    private fun initViews() {

        initObserves()

        binding.rvShort.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val iv_detail: ImageView = binding.ivDetail

        binding.ivCloase.setOnClickListener {
            ActivityCompat.finishAfterTransition(requireActivity())
        }

        val extras = requireActivity().intent.extras
        val show_id = extras!!.getLong("show_id")
        val show_img = extras.getString("show_img")
        val show_name = extras.getString("show_name")
        val show_network = extras.getString("show_network")

        viewModel.apiTVShowDetails(show_id.toInt())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = extras.getString("iv_movie")
            iv_detail.transitionName = imageTransitionName
        }

        binding.tvName.text = show_name
        binding.tvType.text = show_network
        Glide.with(this).load(show_img).into(iv_detail)
    }


    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowDetails.observe(requireActivity(), {
            Logger.d(TAG, "ewrwe" + it.toString())
            refreshAdapter(it.tvShow.pictures)
            binding.tvDetails.text = it.tvShow.description
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
    }

    private fun refreshAdapter(items: List<String>) {
        val adapter = TVShortAdapter( items)
        binding.rvShort.adapter = adapter
    }
}