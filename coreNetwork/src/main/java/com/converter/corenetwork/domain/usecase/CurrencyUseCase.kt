package com.converter.corenetwork.domain.usecase

import android.util.Log
import com.converter.corenetwork.domain.model.CurrencyData
import com.converter.corenetwork.domain.model.CurrencyModel
import com.converter.corenetwork.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import javax.inject.Inject

/**
 * Created by sathish on 30,April,2024
 */
class CurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

    private var decimalFormat: NumberFormat = DecimalFormat("#0.000")

    operator fun invoke(): Flow<CurrencyData> = flow {
        val rates= currencyRepository.loadCurrency().rates
        val currencyList = ArrayList<CurrencyModel>()
        try {
            val response = JSONObject(rates.toString())
            val currencyKeys = response.keys()
            while (currencyKeys.hasNext()) {
                val currencyCode = currencyKeys.next().toString()
                val format = BigDecimal(response.optDouble(currencyCode))
                val currencyAmount = decimalFormat.format(format)
                currencyList.add(CurrencyModel(currencyCode, currencyAmount.toString()))
            }
            emit(CurrencyData(currencyList))
        } catch (exception: Exception) {
           Log.e("Exception",exception.message.toString())
        }

    }
}