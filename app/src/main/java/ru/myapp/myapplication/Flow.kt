package ru.myapp.myapplication

import android.util.Log
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.sample
import kotlin.concurrent.fixedRateTimer

//по сути ничего нового я не изобрел, просто обертка над готовым решением
@OptIn(FlowPreview::class)
fun <T> Flow<T>.throttleLatest(duration: Long, action: (() -> Unit)?): Flow<T> {
    if (action != null) action() // так чисто формально, для перегрузки
    return this.sample(duration)
}

fun <T> Flow<T>.throttleLatest(duration: Long): Flow<T> {
    return channelFlow {
        var lastValue: T? = null
        var hasValue = false
        val timer = fixedRateTimer(daemon = true, period = duration) {
            if (hasValue) {
                lastValue?.let { value ->
                    trySend(value)
                    lastValue = null
                    hasValue = false
                }
            }
        }

        collect { value ->
            lastValue = value
            hasValue = true
        }

        if (hasValue) {
            lastValue?.let {
                send(it)
            }
        }

        timer.cancel()
    }
}

fun <T> Flow<T>.throttleFirst(duration: Long): Flow<T> {
    return flow {
        var lastEmitTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmitTime >= duration) {
                lastEmitTime = currentTime
                emit(value)
            }
        }
    }
}

class Flows {

    val flows = flow {
        for (i in 0..20) {
            emit(i)
            delay(200)
        }
    }

    suspend fun collectFlow() {
        val lat = flows.throttleLatest(300)
        val fir = flows.throttleFirst(300)

        lat.collect {
            Log.d("VAL Latest", it.toString())
        }

        fir.collect {
            Log.d("VAL First", it.toString())
        }
    }

}