package com.wispapp.gcpsoftwaretestapp.ui.main

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wispapp.gcpsoftwaretestapp.R
import com.wispapp.gcpsoftwaretestapp.core.di.Injectable
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.Function
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuItemModel
import com.wispapp.gcpsoftwaretestapp.ui.base.BaseFragment
import com.wispapp.gcpsoftwaretestapp.ui.image.ImageFragment
import com.wispapp.gcpsoftwaretestapp.ui.text.TextFragment
import com.wispapp.gcpsoftwaretestapp.ui.url.WebViewFragment
import com.wispapp.gcpsoftwaretestapp.ui.viewmodels.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_drawer.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {

    private lateinit var menuViewModel: MenuViewModel

    @Inject
    fun inject(viewModel: MenuViewModel) {
        menuViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuViewModel.getMenu()

        menuViewModel.menuLiveData.observe(this, Observer { menuItems ->
            menuItems.forEach { addTitleToNavDrawer(it) }
        })
    }

    private fun addTitleToNavDrawer(menuItem: MenuItemModel) {
        val params = createLayoutParams()
        val view = createTextView(menuItem, params)
        val fragment = getFragmentFromState(menuItem)

        view.setOnClickListener {
            openFragment(fragment)
            nav_drawer.closeDrawers()
        }

        nav_drawer_layout.addView(view)
    }

    private fun createLayoutParams(): LinearLayout.LayoutParams {
        val margin = resources.getDimension(R.dimen.nav_drawer_title_margin).toInt()
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(margin, margin, margin, margin)
        }
    }

    private fun createTextView(
        menuItem: MenuItemModel,
        params: LinearLayout.LayoutParams
    ): TextView {
        val view = TextView(this)
        view.text = menuItem.name
        view.gravity = Gravity.CENTER_HORIZONTAL
        view.textSize = resources.getDimension(R.dimen.nav_drawer_title_text_size)
        view.layoutParams = params
        return view
    }

    private fun getFragmentFromState(menuItem: MenuItemModel): BaseFragment {
        return when (menuItem.function) {
            Function.TEXT -> TextFragment.newInstance(menuItem.param)
            Function.IMAGE -> ImageFragment.newInstance(menuItem.param)
            Function.URL -> WebViewFragment.newInstance(menuItem.param)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
