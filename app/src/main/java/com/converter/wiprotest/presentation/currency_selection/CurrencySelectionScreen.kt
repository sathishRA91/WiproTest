package com.converter.wiprotest.presentation.currency_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.converter.wiprotest.presentation.ui.customfield.CustomOutlineText
import com.converter.wiprotest.presentation.ui.theme.WiproTestTheme

/**
 * Created by sathish on 30,April,2024
 */

@Composable
fun CurrencySelection(navController: NavController)
{

    Box(modifier = Modifier
        .background(color = Color.White)
        .padding(10.dp)
        .fillMaxSize()) {
        Column(horizontalAlignment = Alignment.End,
            modifier = Modifier.wrapContentSize()) {

            Text(modifier = Modifier
                .wrapContentWidth()
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),text = "Currency Search",
                fontSize = 18.sp, color = Color.Black)

            CustomOutlineText( modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), placeholder = "Please enter country code", value = "",{})

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(3){
                    Card {
                        Text(text = "Function Not Implemented")
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun CurrencySelectionScreenPreview()
{
    WiproTestTheme {

        CurrencySelection(rememberNavController())
    }
}