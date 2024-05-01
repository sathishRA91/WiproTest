package com.converter.corenetwork.domain.repository

import com.converter.corenetwork.data.model.CurrencyModelDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by sathish on 30,April,2024
 */
interface CurrencyRepository {

suspend fun loadCurrency():CurrencyModelDto

}