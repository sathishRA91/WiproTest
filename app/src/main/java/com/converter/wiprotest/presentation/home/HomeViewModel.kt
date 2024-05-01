package com.converter.wiprotest.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.converter.corenetwork.domain.model.CurrencyData
import com.converter.corenetwork.domain.usecase.CurrencyUseCase
import com.converter.wiprotest.presentation.util.ResultResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyUseCase: CurrencyUseCase)  : ViewModel() {

    var currencyAmount = mutableStateOf("1")

    var currencyCode = mutableStateOf("USD")

    private var resultResponse = MutableStateFlow<ResultResource<CurrencyData>?>(null)

    val getResultResponse: StateFlow<ResultResource<CurrencyData>?> = resultResponse

    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            currencyUseCase.invoke().onStart {
                resultResponse.value = ResultResource.Loading()
            }.catch {
                resultResponse.value = ResultResource.ErrorMessage(it.message)
            }.collectLatest {
                resultResponse.value = ResultResource.Success(it)
            }
        }
    }
}