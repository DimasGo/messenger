package com.example.telegram.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import com.example.telegram.R

class OptionsFragment : BaseFragment(R.layout.fragment_options) {

    override fun onResume() {
        super.onResume()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.setting_menu, menu)
    }
}