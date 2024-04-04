package com.example.studentapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.studentapp.R

fun createNotifChannel(context: Context, priority:Int, showBadge:Boolean, description:String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = context.getString(R.string.app_name)

        val channelID = "${context.packageName}-${name}"

        val channel = NotificationChannel(channelID, name, priority)

        channel.description = description
        channel.setShowBadge(showBadge)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}