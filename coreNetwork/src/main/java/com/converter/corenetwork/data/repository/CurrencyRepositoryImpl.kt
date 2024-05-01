package com.converter.corenetwork.data.repository

import com.converter.corenetwork.data.model.CurrencyModelDto
import com.converter.corenetwork.data.remotedatasource.RemoteDataSource
import com.converter.corenetwork.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
class CurrencyRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    CurrencyRepository {
    override suspend fun loadCurrency(): CurrencyModelDto {
       return remoteDataSource.loadCurrency()
    }
}