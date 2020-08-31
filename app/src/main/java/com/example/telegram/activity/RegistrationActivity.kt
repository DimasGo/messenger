package com.example.telegram.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.telegram.R
import com.example.telegram.databinding.ActivityRegistrationBinding
import com.example.telegram.ui.fragment.PhoneLoginFragment
import com.example.telegram.ui.utility.initFirebase
import com.example.telegram.ui.utility.replaceFragment

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegistrationBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()

        mToolbar = mBinding.registerToolBar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_yout_phone)
        replaceFragment(PhoneLoginFragment())
    }
}