package ru.myapp.home.domain

import ru.myapp.home.data.Repository
import javax.inject.Inject

class GetResponseUseCase @Inject constructor(private val repository: Repository) {
    fun execute(): String {
        return repository.getBothResponse()
    }
}