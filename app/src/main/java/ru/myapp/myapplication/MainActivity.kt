package ru.myapp.myapplication

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        val currentTime = SystemClock.uptimeMillis()
        calculateLaunchingTime(currentTime)
    }

    private fun calculateLaunchingTime(appearedTime: Long) {
        val uri = "content://ru.myapp.myapplication.launch-provider".toUri()

        contentResolver.query(uri, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val appStartTime = cursor.getLong(0)
                val launchTime: Long by KDelegate(appStartTime, appearedTime)
                Log.d("TIME!", launchTime.toString())
            }
        }
    }
}