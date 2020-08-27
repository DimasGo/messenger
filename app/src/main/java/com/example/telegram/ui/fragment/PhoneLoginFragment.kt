package com.example.telegram.ui.fragment

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.ui.utility.replaceActivity
import com.example.telegram.ui.utility.replaceFragment
import com.example.telegram.ui.utility.showToat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_phone_login.*
import java.util.concurrent.TimeUnit

class PhoneLoginFragment : Fragment(R.layout.fragment_phone_login) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth
    override fun onStart() {
        super.onStart()

        image_registration_next.setOnClickListener { nextFragment() }

        mAuth = FirebaseAuth.getInstance()
        mCallback = object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener{
                    if (it.isSuccessful){
                        showToat("Hello")
                        (activity as RegistrationActivity).replaceActivity(MainActivity())
                    } else {
                        showToat(it.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToat(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(ConfirmSmsFragment(mPhoneNumber, id))
            }
        }
    }

    private fun nextFragment() {
        if (setting_phone_edit_text.text.toString().isEmpty()) {
            showToat("Enter number phone")
        } else

            authUser()
          //  replaceFragment(ConfirmSmsFragment())
    }

    private fun authUser() {
        mPhoneNumber = setting_phone_edit_text.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mPhoneNumber, 60, TimeUnit.SECONDS, activity as RegistrationActivity, mCallback)
    }
}