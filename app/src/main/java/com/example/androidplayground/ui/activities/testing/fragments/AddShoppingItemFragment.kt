package com.example.androidplayground.ui.activities.testing.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidplayground.R
import com.example.androidplayground.databinding.FragmentAddShoppingItemBinding
import com.example.androidplayground.viewmodel.testing.ShoppingViewModel


class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    val viewModel: ShoppingViewModel by viewModels()
    lateinit var binding: FragmentAddShoppingItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddShoppingItemBinding.bind(view)

        val fabAddShoppingItem = binding.ivShoppingImage
        fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}