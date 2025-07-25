package ru.myapp.myapplication

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

/** Задача 1*/
suspend fun request(): String {
    delay(500)
    return "RESULT"
}

/** Задача 2 */
fun timer(): Observable<Long> {
    return Observable.interval(1, TimeUnit.SECONDS)
}