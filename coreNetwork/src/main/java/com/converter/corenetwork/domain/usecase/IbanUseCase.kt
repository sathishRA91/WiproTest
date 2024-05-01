package com.converter.corenetwork.domain.usecase

import com.converter.corenetwork.data.model.toDomainModel
import com.converter.corenetwork.domain.model.IbanModel
import com.converter.corenetwork.domain.repository.IbanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
class IbanUseCase @Inject constructor(private val ibanRepository: IbanRepository)
{
    operator fun invoke(ibanNumber: String): Flow<IbanModel> = flow {
        emit(ibanRepository.validateIban(ibanNumber).toDomainModel())
    }
}