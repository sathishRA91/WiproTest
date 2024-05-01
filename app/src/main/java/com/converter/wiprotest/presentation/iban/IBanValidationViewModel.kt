package com.converter.wiprotest.presentation.iban

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.converter.corenetwork.domain.model.IbanModel
import com.converter.corenetwork.domain.usecase.IbanUseCase
import com.converter.wiprotest.presentation.util.ResultResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by sathish on 01,May,2024
 */
@HiltViewModel
class IBanValidationViewModel @Inject constructor(private val ibanUseCase: IbanUseCase) :  ViewModel(){

    var ibanNumber = mutableStateOf("")
    var ibanNumberError = mutableStateOf(false)

    private var resultResponse = MutableStateFlow<ResultResource<IbanModel>?>(null)
    val getResultResponse = resultResponse.asStateFlow()

    fun validateIban()
    {
        viewModelScope.launch {
            ibanUseCase.invoke(ibanNumber.value).onStart {
                resultResponse.value = ResultResource.Loading()
            }.catch {
                resultResponse.value = ResultResource.ErrorMessage(it.message)
            }.collect {
                ibanNumberError.value=false
                resultResponse.value=ResultResource.Success(it)
            }
        }
    }

}