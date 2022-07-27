package com.exsample.android_imperative.ui.fragments.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exsample.android_imperative.R
import com.exsample.android_imperative.adapter.HistoryAdapter
import com.exsample.android_imperative.databinding.FragmentHistoryBinding
import com.exsample.android_imperative.model.TVShow
import com.exsample.android_imperative.ui.fragments.BaseFragment
import com.exsample.android_imperative.utils.Logger

class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val TAG = "HistoryFragment"
    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
        initViews()
    }

    private fun initViews() {

        viewModel.getTVSowFromDB()

        initObserves()
    }

    private fun initObserves() {
        // Room Related
        viewModel.tvShowsFromDB.observe(requireActivity(), {
            Logger.d(TAG, it.toString())
            refreshAdapter(it)
        })


    }

    private fun refreshAdapter(items: List<TVShow>) {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = HistoryAdapter(items as ArrayList)
        binding.recyclerView.adapter = adapter
    }

}