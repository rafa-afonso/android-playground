package com.example.androidplayground.ui.activities.testing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.androidplayground.adapters.testing.ImageAdapter
import com.example.androidplayground.ui.activities.testing.fragments.AddShoppingItemFragment
import com.example.androidplayground.ui.activities.testing.fragments.ImagePickFragment
import javax.inject.Inject

class ShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}