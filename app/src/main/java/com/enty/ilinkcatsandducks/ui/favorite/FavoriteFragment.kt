package com.enty.ilinkcatsandducks.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enty.ilinkcatsandducks.R
import com.enty.ilinkcatsandducks.data.db.FavoriteDataBase
import com.enty.ilinkcatsandducks.databinding.FragmentFavoriteBinding
import com.enty.ilinkcatsandducks.ui.adapters.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment (): Fragment()  {

    private val viewModel: FavoriteViewModel by viewModels()
    lateinit var binding: FragmentFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val adapter = FavoriteAdapter(requireContext())
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favImages.observe(viewLifecycleOwner, Observer{
            adapter.submitList(it)
        })

        return binding.root
    }


}