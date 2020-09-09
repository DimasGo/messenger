package com.example.telegram.ui.fragment

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity

open class BaseFragment(val layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()

        (activity as MainActivity).mAppDrawer.disebleDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enbledDrawer()

    }
}