package com.converter.wiprotest.presentation.util

/**
 * Created by sathish on 30,April,2024
 */

sealed class Screens(val route:String)
{
    object Home:Screens("home")
    object CurrencySelection:Screens("currencySelection")
    object IbanScreen:Screens("iban_screen")
}