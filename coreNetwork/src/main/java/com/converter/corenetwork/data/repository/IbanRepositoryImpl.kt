package com.converter.corenetwork.data.repository

import com.converter.corenetwork.data.model.IbanModelDto
import com.converter.corenetwork.data.remotedatasource.RemoteDataSource
import com.converter.corenetwork.domain.repository.IbanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
class IbanRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    IbanRepository {
    override suspend fun validateIban(ibanNumber:String): IbanModelDto {
       return remoteDataSource.validateIban(ibanNumber)
    }
}