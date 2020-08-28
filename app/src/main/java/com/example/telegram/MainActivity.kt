package com.example.telegram

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.telegram.activity.RegistrationActivity
import com.example.telegram.databinding.ActivityMainBinding
import com.example.telegram.ui.`object`.AppDrawer
import com.example.telegram.ui.fragment.ChatFragment
import com.example.telegram.ui.utility.AUTH
import com.example.telegram.ui.utility.SendNotification
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolBar: Toolbar
    private lateinit var mAppDrawer: AppDrawer


    private var channelID = "com.example.notification"
    private var channelName = "CODELY_CHANNEL"

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

    override fun onStop() {
        super.onStop()

    }

    private fun initFunc() {
        if (AUTH.currentUser!= null) {
            setSupportActionBar(mToolBar)
            mAppDrawer.createMenu()
            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContent, ChatFragment()).commit()
        } else {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun initFieles() {
        mToolBar = mBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolBar)

        AUTH = FirebaseAuth.getInstance()
    }

}