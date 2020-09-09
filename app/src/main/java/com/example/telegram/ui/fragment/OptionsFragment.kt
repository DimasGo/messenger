package com.example.telegram.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.model.User
import com.example.telegram.ui.utility.AUTH
import com.example.telegram.ui.utility.USER
import com.example.telegram.ui.utility.replaceActivity
import com.example.telegram.ui.utility.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : BaseFragment(R.layout.fragment_options) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        set_bio.text = USER.bio
        set_name_account.text = USER.fullname
        set_phone_number.text = USER.phone
        setting_status.text = USER.status
        name_profile.text = USER.username
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
            R.id.edit_username -> {
                replaceFragment(ChangeUsername())
            }
        }
        return true
    }
}