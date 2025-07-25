package ru.myapp.myapplication

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel : ViewModel() {
    val obs = BehaviorSubject.create<String>()
}