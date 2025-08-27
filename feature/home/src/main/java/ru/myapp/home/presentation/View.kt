package ru.myapp.home.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.myapp.home.domain.GetResponseUseCase
import javax.inject.Inject

class View @Inject constructor(private val useCase: GetResponseUseCase) {
    private val _responseFlow = MutableStateFlow("")
    val responseFlow = _responseFlow.asStateFlow()

    init {
        getResponse()
    }

    private fun getResponse() {
        _responseFlow.value = useCase.execute()
    }
}