package com.wispapp.gcpsoftwaretestapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment

class WebViewFragment : BaseFragment() {

    companion object {

        private const val ARG_URL = "url"

        fun newInstance(url: String): WebViewFragment {
            val fragment =
                WebViewFragment()
            val args = Bundle().apply {
                putString(ARG_URL, url)
            }
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun loadContent() {
        val url = arguments?.getString(ARG_URL)
    }
}
