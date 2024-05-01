package com.converter.wiprotest.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.converter.corenetwork.domain.model.CurrencyData
import com.converter.corenetwork.domain.model.CurrencyModel
import com.converter.wiprotest.presentation.ui.customfield.CustomOutlineText
import com.converter.wiprotest.presentation.ui.customfield.CustomText
import com.converter.wiprotest.presentation.ui.theme.WiproTestTheme
import com.converter.wiprotest.presentation.util.ResultResource
import com.converter.wiprotest.presentation.util.Screens
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * Created by sathish on 30,April,2024
 */

 val decimalFormat: NumberFormat = DecimalFormat("#0.00")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,homeViewModel: HomeViewModel = hiltViewModel())
{
    val homeState by homeViewModel.getResultResponse.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()) {
        Column(horizontalAlignment =Alignment.End,
            modifier = Modifier.wrapContentSize()) {
            TopAppBar(title = {},
                actions = { IconButton(onClick = { navController.navigate(Screens.IbanScreen.route) }) {
                    Icon(modifier = Modifier.size(25.dp),imageVector = Icons.Default.Info, contentDescription ="Info" )
                }},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary, actionIconContentColor = Color.White))

            Text(modifier = Modifier
                .wrapContentWidth()
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),text = "Currency Converter",
                fontSize = 18.sp, color = Color.Black)

            CustomOutlineText( modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), placeholder = "Please enter amount", value = TextFieldValue(
                text = homeViewModel.currencyAmount.value,
                    selection = TextRange(homeViewModel.currencyAmount.value.length)
            ).text,{
                   homeViewModel.currencyAmount.value=it
                    currencyConvert(it, homeState?.data!!.rates)
            })

            CustomOutlineText(modifier = Modifier
                .width(130.dp)
                .height(70.dp)
                .padding(10.dp), placeholder = "USD", value = "USD",{},
                enable = false,
                trailingIcon = { Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "") },
                onClick = {navController.navigate(Screens.CurrencySelection.route)})

            LoadCurrencyList(homeState)
        }

    }
}

@Composable
private fun CountryPriceItem(currencyList:ArrayList<CurrencyModel>)
{
    LazyVerticalGrid(columns = GridCells.Fixed(3),modifier = Modifier.fillMaxSize()) {

        items(currencyList){
            Card(modifier = Modifier.padding(8.dp),
                border = BorderStroke(1.dp, color = Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                CustomText(modifier = Modifier.padding(7.dp),value = it.rate)
                CustomText(modifier = Modifier.padding(7.dp),value = it.base)
            }
        }
    }

}

@Composable
private fun LoadCurrencyList(homeState:ResultResource<CurrencyData>?)
{
    when(homeState)
    {
        is ResultResource.ErrorMessage -> CircularProgressIndicator()
        is ResultResource.Loading -> CircularProgressIndicator()
        is ResultResource.Success -> {
            CountryPriceItem(homeState.data!!.rates)
        }
        else -> {}
    }
}

private fun currencyConvert(amount: String,currencyList:ArrayList<CurrencyModel>?) {

    if (currencyList != null) {
        amount.let { value ->
            if (value.isNotEmpty()) {
                currencyList.map {
                    it.also { value ->
                        value.base = it.base
                        value.rate = decimalFormat.format(it.rate.toDouble() * amount.toDouble()).toString()
                    }
                }
            } else {
                currencyList.map {
                    it.also { value ->
                        value.base = it.base
                        value.rate = "0.0"
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview()
{
    WiproTestTheme {

        HomeScreen(rememberNavController())
    }
}