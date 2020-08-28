package com.example.telegram.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.ui.utility.AUTH
import com.example.telegram.ui.utility.replaceActivity
import com.google.firebase.auth.FirebaseAuth

class OptionsFragment : BaseFragment(R.layout.fragment_options) {

    override fun onResume() {
        super.onResume()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.setting_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exit_account_button ->  {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegistrationActivity())
            }
        }
        return true
    }
}