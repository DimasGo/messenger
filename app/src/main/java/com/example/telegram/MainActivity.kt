package com.example.telegram

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.databinding.ActivityMainBinding
import com.example.telegram.model.User
import com.example.telegram.ui.`object`.AppDrawer
import com.example.telegram.ui.fragment.ChatFragment
import com.example.telegram.utility.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolBar: Toolbar


    //private var channelID = "com.example.notification"
    //private var channelName = "CODELY_CHANNEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFieles()
        initFunc()
        //SendNotification(channelID, channelName, "Notification", "Telegram is open")
    }


    private fun initFunc() {
        if (AUTH.currentUser!= null) {
            setSupportActionBar(mToolBar)
            mAppDrawer.createMenu()
            replaceFragment(ChatFragment())
        } else {
            replaceActivity(RegistrationActivity())
        }
    }


    private fun initFieles() {
        mToolBar = mBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolBar)

        initFirebase()
        initUser()
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
            USER = it.getValue(User::class.java) ?: User()
        })
    }


    private fun TestBranchFun(){
        replaceActivity(RegistrationActivity())

    }
}