package com.example.androidplayground.ui.activities.testing.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.androidplayground.R
import com.example.androidplayground.databinding.FragmentAddShoppingItemBinding
import com.example.androidplayground.util.Resource
import com.example.androidplayground.viewmodel.testing.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_add_shopping_item) {

    val viewModel: ShoppingViewModel by viewModels()
    lateinit var binding: FragmentAddShoppingItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddShoppingItemBinding.bind(view)
        subscribeToObservers()

        val btnAddShoppingItem = binding.btnAddShoppingItem
        val etShoppingItemName = binding.etShoppingItemName
        val etShoppingItemAmount = binding.etShoppingItemAmount
        val etShoppingItemPrice = binding.etShoppingItemPrice
        btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                etShoppingItemName.text.toString(),
                etShoppingItemAmount.text.toString(),
                etShoppingItemPrice.text.toString()
            )
        }

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

    private fun subscribeToObservers() {
        val ivShoppingImage = binding.ivShoppingImage
        val rootLayout = binding.root

        viewModel.curImageUrl.observe(viewLifecycleOwner, Observer {
            glide.load(it).into(ivShoppingImage)
        })

        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success<*> -> {
                        Snackbar.make(
                            rootLayout,
                            "Added Shopping Item",
                            Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    }

                    is Resource.Error<*> -> {
                        Snackbar.make(
                            rootLayout,
                            response.message ?: "An unknown error has occurred",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    is Resource.Loading<*> -> {
                        /* NO-OP */
                    }
                }
            }
        })
    }
}