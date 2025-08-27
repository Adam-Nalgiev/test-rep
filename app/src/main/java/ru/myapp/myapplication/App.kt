package ru.myapp.myapplication

import android.app.Application

class App: Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}