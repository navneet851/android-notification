package com.android.shop.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

//this function needs if android version >= Oreo 8.0 api level 26
fun notificationChannel(context: Context){
    //check
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "notify"
        val description = "simple notification"
        val importance = NotificationManager.IMPORTANCE_MIN

        val channel = NotificationChannel(
            "1",
            name,
            importance
        )

        val notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun showNotification(context: Context){
    //intent to open activity in notification click
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    //norification build
    val builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("first notification")
        .setContentText("First")
        .setPriority(NotificationCompat.PRIORITY_MIN)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)){
        notify(1, builder)
    }

}