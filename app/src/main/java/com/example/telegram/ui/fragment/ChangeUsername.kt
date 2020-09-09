package com.example.telegram.ui.fragment

import android.view.*
import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.ui.utility.*
import kotlinx.android.synthetic.main.fragment_change_username.*

class ChangeUsername : BaseFragment(R.layout.fragment_change_username) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)

        val fullnameList = USER.fullname.split(" ")
        setting_username_text.setText(fullnameList[0])
        setting_surname_text.setText(fullnameList[1])
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.confirm_username, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.confirm_button -> changeName()
        }
        return true
    }

    private fun changeName() {
        val name = setting_username_text.text.toString()
        val surname = setting_surname_text.text.toString()

        if (name.isEmpty()) {
            showToat("Please, write your name")
        } else {
            val fullname = "$name $surname"

            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(USER_FULLNAME)
                .setValue(fullname).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToat("Data update")
                        USER.fullname = fullname
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}