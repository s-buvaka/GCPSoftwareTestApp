package com.wispapp.gcpsoftwaretestapp.ui.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.core.di.Injectable
import com.wispapp.gcpsoftwaretestapp.core.extensions.ViewModelClassMap
import com.wispapp.gcpsoftwaretestapp.core.extensions.sharedViewModel
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment
import com.wispapp.gcpsoftwaretestapp.ui.viewmodels.MenuViewModel
import kotlinx.android.synthetic.main.fragment_text.*
import javax.inject.Inject

class TextFragment : BaseFragment(), Injectable {

    private lateinit var classMap: ViewModelClassMap
    private lateinit var factory: ViewModelProvider.Factory

    private val menuViewModel: MenuViewModel by lazy {
        sharedViewModel<MenuViewModel>(factory, classMap)
    }

    @Inject
    fun inject(classMap: ViewModelClassMap, factory: ViewModelProvider.Factory) {
        this.classMap = classMap
        this.factory = factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun loadContent() {
        val title = arguments?.getString(ARG_MENU_TITLE)

        menuViewModel.menuLiveData.observe(this, Observer { menuItems ->
            val menuItem = menuItems.find { it.name == title }
            menuItem?.let { param_text_view.text = it.param }
        })
    }

    companion object {

        private const val ARG_MENU_TITLE = "title"

        fun newInstance(title: String): TextFragment {
            val fragment = TextFragment()
            val args = Bundle().apply {
                putString(ARG_MENU_TITLE, title)
            }
            fragment.arguments = args

            return fragment
        }
    }
}
