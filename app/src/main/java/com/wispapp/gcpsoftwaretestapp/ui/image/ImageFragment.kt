package com.wispapp.gcpsoftwaretestapp.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.core.common.ImageLoaderImpl
import com.wispapp.gcpsoftwaretestapp.core.di.Injectable
import com.wispapp.gcpsoftwaretestapp.core.executors.AppExecutors
import com.wispapp.gcpsoftwaretestapp.core.extensions.ViewModelClassMap
import com.wispapp.gcpsoftwaretestapp.core.extensions.sharedViewModel
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment
import com.wispapp.gcpsoftwaretestapp.ui.viewmodels.MenuViewModel
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class ImageFragment : BaseFragment(), Injectable {

    private lateinit var classMap: ViewModelClassMap
    private lateinit var factory: ViewModelProvider.Factory

    private val menuViewModel: MenuViewModel by lazy {
        sharedViewModel<MenuViewModel>(factory, classMap)
    }

    private val imageLoader = ImageLoaderImpl(AppExecutors())

    @Inject
    fun inject(classMap: ViewModelClassMap, factory: ViewModelProvider.Factory) {
        this.classMap = classMap
        this.factory = factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun loadContent() {
        val title = arguments?.getString(ARG_MENU_TITLE)

        menuViewModel.menuLiveData.observe(this, Observer { menuItems ->
            val menuItem = menuItems.find { it.name == title }
            menuItem?.let { imageLoader.loadImage(it.param, param_image_view) }
        })
    }

    companion object {

        private const val ARG_MENU_TITLE = "title"

        fun newInstance(title: String): ImageFragment {
            val fragment =
                ImageFragment()
            val args = Bundle().apply {
                putString(ARG_MENU_TITLE, title)
            }
            fragment.arguments = args

            return fragment
        }
    }
}
