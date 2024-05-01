package com.converter.corenetwork.data.network

import com.converter.corenetwork.constant.ApiConfig
import com.converter.corenetwork.data.model.CurrencyModelDto
import com.converter.corenetwork.data.model.IbanModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sathish on 30,April,2024
 */
interface ApiInterface {

    @GET(ApiConfig.GET_CURRENCY)
    suspend fun getCurrency(@Query("base") base:String):CurrencyModelDto


    @GET(ApiConfig.VALIDATE_IBAN)
    suspend fun validateIban(@Query("iban_number") ibanNumber:String):IbanModelDto
}