package com.converter.corenetwork.domain.repository

import com.converter.corenetwork.data.model.IbanModelDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by sathish on 30,April,2024
 */
interface IbanRepository {

    suspend fun validateIban(ibanNumber: String):IbanModelDto

}