package com.wispapp.gcpsoftwaretestapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : BaseFragment() {

    companion object {

        private const val ARG_TEXT = "text"

        fun newInstance(text: String): TextFragment {
            val fragment = TextFragment()
            val args = Bundle().apply {
                putString(ARG_TEXT, text)
            }
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun loadContent() {
        val text = arguments?.getString(ARG_TEXT)
        param_text_view.text = text
    }
}
