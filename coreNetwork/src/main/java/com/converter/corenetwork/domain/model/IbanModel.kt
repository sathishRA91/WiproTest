package com.converter.corenetwork.domain.model

data class IbanModel(
    val bankData: BankData,
    val iban: String,
    val ibanData: IbanData,
    val message: String,
    val valid: Boolean
)
