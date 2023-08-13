package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)
        //activity ochib berish uchun
        val paddingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        
        val notification = NotificationCompat.Builder(this, "1")
        notification.setSmallIcon(R.drawable.ic_launcher_foreground)
        notification.setContentTitle("Bizning notification")
        //     notification.setContentText("bugun 18:00 darsingiz bor esdan chiqmasin")
        notification.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Agar darsga kelmasangiz mamuriy javobgarlik torisidagi qonunga binon javobgarlikka tortishilingiz mumkun")
        )
        notification.setContentIntent(paddingIntent)
        notification.setAutoCancel(true)//activity ga kirganda notification yoqolib ketishi



        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //versiya
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name) //qaysi app dan chiqishini bilish uchun
            val descriptionText = getString(R.string.app_name)//bunga ham app_name ni berip turipmiz
            val importanse = NotificationManager.IMPORTANCE_DEFAULT//muhimliligi importans default
            val channel = NotificationChannel("1", name, importanse).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
        //yozish
        binding.showNotification.setOnClickListener {
            notificationManager.notify(1, notification.build())
        }
        //ochirish
        binding.hideNotification.setOnClickListener {
            notificationManager.cancel(1)
        }
    }
}