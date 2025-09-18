package ru.myapp.myapplication

import android.os.Handler
import android.os.Looper
import android.os.Process
import java.util.concurrent.atomic.AtomicBoolean

class WorkerThread(name: String? = null) : Thread() {
    private val handler: Handler = Handler(Looper.myLooper()!!)
    private val isRunning = AtomicBoolean(false)
    private val isPaused = AtomicBoolean(false)
    private val pauseLock = Object()

    init {
        this.name = name.toString()
        priority = Process.THREAD_PRIORITY_BACKGROUND
    }

    override fun run() {
        Looper.prepare()
        isRunning.set(true)
        Looper.loop()
    }

    fun post(task: Runnable) {
        if (!isRunning.get()) {
            throw IllegalStateException("Не заупщен")
        }
        handler.post {
            synchronized(pauseLock) {
                while (isPaused.get()) {
                    try {
                        pauseLock.wait()
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                        return@post
                    }
                }
            }
            task.run()
        }
    }

    fun postDelayed(task: Runnable, delay: Long) {
        if (!isRunning.get()) {
            throw IllegalStateException("Не заупщен")
        }

        handler.postDelayed({
            synchronized(pauseLock) {
                while (isPaused.get()) {
                    try {
                        pauseLock.wait()
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                        return@postDelayed
                    }
                }
            }
            task.run()
        }, delay)
    }

    fun execute(task: Runnable){
        post(task)
    }

    fun pause() {
        isPaused.set(true)
    }

    fun resumeThread() {
        synchronized(pauseLock) {
            isPaused.set(false)
            pauseLock.notifyAll()
        }
    }

    fun quit() {
        isRunning.set(false)
        handler.looper.quitSafely()
    }

}