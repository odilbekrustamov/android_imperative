package com.exsample.android_imperative.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
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

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding
    private val TAG = "DetailsFragment"
    private lateinit var show_id: String
    private lateinit var show_img: String
    private lateinit var show_name: String
    private lateinit var show_network: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            show_id = it.get("show_id").toString()
            show_img = it.get("show_img").toString()
            show_name = it.get("show_name").toString()
            show_network = it.get("show_network").toString()
            Log.d(TAG, "onCreate: ${show_id}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        initViews()
    }
    private fun initViews() {
        initObserves()

        binding.rvShort.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        val iv_detail: ImageView = binding.ivDetail
        binding.ivCloase.setOnClickListener {
            ActivityCompat.finishAfterTransition(requireActivity())
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          //  val imageTransitionName = extras.getString("iv_movie")
           // iv_detail.transitionName = imageTransitionName
        }

        binding.tvName.text = show_name
        binding.tvType.text = show_network
        Glide.with(this).load(show_img).into(iv_detail)

        viewModel.apiTVShowDetails(show_id.toInt())
    }

    private fun refreshAdapter(items: List<String>) {
        val adapter = TVShortAdapter(items)
        binding.rvShort.adapter = adapter
    }

    private fun initObserves() {
        // Retrofit Related
        viewModel.tvShowDetails.observe(requireActivity(), {
            Logger.d(TAG, it.toString())
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

}