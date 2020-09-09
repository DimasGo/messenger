package com.example.telegram.ui.fragment

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.ui.utility.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_confirm_sms.*

class ConfirmSmsFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_confirm_sms) {

    override fun onStart() {
        super.onStart()
        (activity as RegistrationActivity).title = phoneNumber
        registration_text_code.addTextChangedListener(AppTextWatcher {
            val string = registration_text_code.text.toString()
            if (string.length == 6) {
                entereCode()
            }
        })
    }

    private fun entereCode() {
        val code = registration_text_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)

        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                val uid = AUTH.currentUser?.uid.toString()
                val dateMAp = mutableMapOf<String, Any>()

                dateMAp[USER_ID] = uid
                dateMAp[USER_PHONE] = phoneNumber
                dateMAp[USERNAME] = id

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMAp)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToat("Welcome")
                            (activity as RegistrationActivity).replaceActivity(MainActivity())
                        } else showToat(it.exception?.message.toString())
                    }

            } else showToat(it.exception?.message.toString())

        }
    }
}
