package ru.myapp.home.data

import ru.myapp.retrofit.Retrofit1
import ru.myapp.retrofit.Retrofit2
import javax.inject.Inject

class Repository @Inject constructor(
    private val firstServ: Retrofit1,
    private val secondServ: Retrofit2
) {

    fun getBothResponse(): String {
        return "${firstServ.getResponse()} and ${secondServ.getResponse()}"
    }
}