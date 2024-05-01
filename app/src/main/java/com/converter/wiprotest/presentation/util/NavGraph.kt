package com.converter.wiprotest.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.converter.wiprotest.presentation.currency_selection.CurrencySelection
import com.converter.wiprotest.presentation.home.HomeScreen
import com.converter.wiprotest.presentation.iban.IBanScreen

/**
 * Created by sathish on 01,May,2024
 */

@Composable
fun NavigationToScreen(navController:NavHostController)
{
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(route= Screens.Home.route)
        {
            HomeScreen(navController = navController)
        }
        composable(route=Screens.CurrencySelection.route)
        {
            CurrencySelection(navController)
        }
        composable(route=Screens.IbanScreen.route)
        {
            IBanScreen(navController = navController)
        }
    }
}