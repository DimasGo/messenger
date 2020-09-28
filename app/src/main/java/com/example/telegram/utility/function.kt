package com.example.telegram.utility

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.telegram.R

fun Fragment.showToat(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    startActivity(Intent(this, activity::class.java))
    this.finish()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    fragmentManager?.beginTransaction()
        ?.replace(R.id.dataContent, fragment)
        ?.addToBackStack(null)
        ?.commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    this.supportFragmentManager.beginTransaction()
        .replace(R.id.dataContent, fragment)
        .addToBackStack(null)
        .commit()
}

fun AppCompatActivity.SendNotification(channelID: String, channelName: String, title: String, textMessage: String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val imp = NotificationManager.IMPORTANCE_HIGH
        val mNotificationChannel = NotificationChannel(channelID, channelName, imp)
        val notificationBuilder = Notification.Builder(this, channelID)
            .setSmallIcon(R.drawable.ic_baseline_call_24)
            .setContentTitle(title)
            .setContentText(textMessage)
            .setAutoCancel(true)

        val notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mNotificationChannel)
        notificationManager.notify(0, notificationBuilder.build())
    }else {
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_baseline_call_24)
            .setContentTitle(title)
            .setContentText(textMessage)
            .setAutoCancel(true)

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

    }
}

