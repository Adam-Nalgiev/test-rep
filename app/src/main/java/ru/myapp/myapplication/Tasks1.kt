package ru.myapp.myapplication

/** Задача 1 **/

/* Ответ
HAHAHA       onSubscribeThread = RxComputationThreadPool-1
HAHAHA       mapThread = RxNewThreadScheduler-1
HAHAHA       flatMapThread = RxSingleScheduler-1
HAHAHA       subscribeThread = RxCachedThreadScheduler-1
 */


/** Задача 2 */

/*
    Ответ:
        Можно заменить на ReplaySubject - он вернет всю историю значений после подписки.
        Вторым решением, наверное, будет заменить его на холодный поток.

        val subject = ReplaySubject.create<String>()
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.subscribe{ Log.d("TAG", it) }
 */