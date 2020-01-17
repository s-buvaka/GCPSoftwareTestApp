package com.wispapp.gcpsoftwaretestapp.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment

class ImageFragment : BaseFragment() {

    companion object {

        private const val ARG_IMAGE_URL = "image_url"

        fun newInstance(imageUrl: String): ImageFragment {
            val fragment =
                ImageFragment()
            val args = Bundle().apply {
                putString(ARG_IMAGE_URL, imageUrl)
            }
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun loadContent() {
        val imageUrl = arguments?.getString(ARG_IMAGE_URL)

    }
}
