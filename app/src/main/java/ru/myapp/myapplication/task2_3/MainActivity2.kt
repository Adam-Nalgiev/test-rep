package ru.myapp.myapplication.task2_3

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.myapp.myapplication.R
import ru.myapp.myapplication.task2_3.worker.ChargingWorker
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
        }
        scheduleChargingWork()
    }

    fun scheduleChargingWork() {
        val chargingWork =
            OneTimeWorkRequestBuilder<ChargingWorker>().setInitialDelay(1, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(chargingWork)
    }
}