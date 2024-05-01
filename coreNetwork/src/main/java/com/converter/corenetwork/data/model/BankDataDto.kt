package com.converter.corenetwork.data.model

import com.google.gson.annotations.SerializedName
import com.converter.corenetwork.domain.model.BankData

data class BankDataDto(
    @SerializedName("bank_code")
    val bankCode: String,
    val bic: String,
    val city: String,
    val name: String,
    val zip: String
)

fun BankDataDto.toDomainModel() = BankData(
    bankCode = bankCode,
    city = city,
    name=name,
    zip=zip)