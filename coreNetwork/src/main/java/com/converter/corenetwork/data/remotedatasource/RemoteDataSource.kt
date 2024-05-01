package com.converter.corenetwork.data.remotedatasource

import com.converter.corenetwork.data.model.CurrencyModelDto
import com.converter.corenetwork.data.model.IbanModelDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by sathish on 30,April,2024
 */
interface RemoteDataSource {

    suspend fun loadCurrency(): CurrencyModelDto

    suspend fun validateIban(ibanNumber: String):IbanModelDto
}