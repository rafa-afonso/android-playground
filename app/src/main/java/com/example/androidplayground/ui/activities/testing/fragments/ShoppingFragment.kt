package com.example.androidplayground.ui.activities.testing.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidplayground.R
import com.example.androidplayground.databinding.FragmentShoppingBinding
import com.example.androidplayground.viewmodel.testing.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    val viewModel: ShoppingViewModel by viewModels()
    lateinit var binding: FragmentShoppingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoppingBinding.bind(view)

        val fabAddShoppingItem = binding.fabAddShoppingItem
        fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }


    }
}