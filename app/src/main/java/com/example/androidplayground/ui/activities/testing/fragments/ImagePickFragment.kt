package com.example.androidplayground.ui.activities.testing.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidplayground.R
import com.example.androidplayground.adapters.testing.ImageAdapter
import com.example.androidplayground.databinding.FragmentImagePickBinding
import com.example.androidplayground.util.Constants.Companion.GRID_SPAN_COUNT
import com.example.androidplayground.viewmodel.testing.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagePickFragment @Inject constructor(
    val imageAdapter: ImageAdapter
) : Fragment(R.layout.fragment_image_pick) {

    val viewModel: ShoppingViewModel by viewModels()
    lateinit var binding: FragmentImagePickBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagePickBinding.bind(view)
        setupRecyclerView()

        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }

    private fun setupRecyclerView() {
        val rvImages = binding.rvImages
        rvImages.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

}