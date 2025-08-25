package ru.myapp.home.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.myapp.home.domain.GetResponseUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getResponseUseCase: GetResponseUseCase
): ViewModel() {
    private val _responseFlow = MutableStateFlow("")
    val responseFlow = _responseFlow.asStateFlow()

    init {
        getResponse()
    }

    private fun getResponse() {
        _responseFlow.value = getResponseUseCase.execute()
    }
}