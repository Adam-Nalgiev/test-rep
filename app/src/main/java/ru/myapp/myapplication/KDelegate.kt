package ru.myapp.myapplication

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class KDelegate(startTime: Long, appearedTime: Long) {

    private val launchTime = appearedTime - startTime

    private val glob = GlobalScope.launch(Dispatchers.IO) {
        logging()
    }

    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): Long {
        return launchTime
    }

    private tailrec suspend fun logging() {
        delay(3000)
        Log.d("TIME!", launchTime.toString())
        logging()
    }
}