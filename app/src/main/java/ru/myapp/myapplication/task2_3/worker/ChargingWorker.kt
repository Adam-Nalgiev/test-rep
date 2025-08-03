package ru.myapp.myapplication.task2_3.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.myapp.myapplication.R

class ChargingWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("START WORK", "DO WORK!")
        if (isDeviceCharging()) {
            createNotChannel()
            showChargingNotification()
        }
        return Result.success()
    }

    private fun isDeviceCharging(): Boolean {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
            applicationContext.registerReceiver(null, filter)
        }
        val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1

        return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
    }

    private fun showChargingNotification() {
        val notification = NotificationCompat.Builder(applicationContext, "charging_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Charging")
            .build()

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        NotificationManagerCompat.from(applicationContext).notify(NOT_ID, notification)
    }

    private fun createNotChannel() {
        val name = "charging notification"
        val importance = NotificationManagerCompat.IMPORTANCE_MAX
        val channel = NotificationChannel("charging_channel", name, importance)

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val NOT_ID = 101
    }
}