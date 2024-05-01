package com.converter.corenetwork.data.remotedatasource

import com.converter.corenetwork.constant.AppConstant
import com.converter.corenetwork.data.model.CurrencyModelDto
import com.converter.corenetwork.data.model.IbanModelDto
import com.converter.corenetwork.data.network.ApiInterface
import com.converter.corenetwork.data.remotedatasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
class RemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    RemoteDataSource {
    override suspend fun loadCurrency(): CurrencyModelDto {
        return withContext(Dispatchers.IO) {
            apiInterface.getCurrency(AppConstant.BASE_CURRENCY)
        }
    }


    override suspend fun validateIban(ibanNumber: String): IbanModelDto {
        return withContext(Dispatchers.IO) {
            apiInterface.validateIban(ibanNumber)
        }

    }
}