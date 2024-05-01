package com.converter.wiprotest.presentation.util

/**
 * Created by sathish on 01,May,2024
 */
object Validation {

    fun isBanNumberValid(number: String): Boolean {
        return number.isNotEmpty() && number.length > 3
    }

}