package com.example.telegram.ui.fragment

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.ui.utility.AppTextWatcher
import com.example.telegram.ui.utility.replaceActivity
import com.example.telegram.ui.utility.showToat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_confirm_sms.*

class ConfirmSmsFragment(val mPhoneNumber: String, val id: String) : Fragment(R.layout.fragment_confirm_sms) {

    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        (activity as RegistrationActivity).title = mPhoneNumber
        registration_text_code.addTextChangedListener(AppTextWatcher {
            val string = registration_text_code.text.toString()
            if (string.length == 6) {
                verevicateCode()
            }
        })
    }

    private fun verevicateCode() {
        val code = registration_text_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)

        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showToat("Hello")
                (activity as RegistrationActivity).replaceActivity(MainActivity())
            } else {
                showToat(it.exception?.message.toString())
            }
        }
    }
}
