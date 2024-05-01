package com.converter.wiprotest.presentation.iban

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.converter.corenetwork.domain.model.IbanModel
import com.converter.wiprotest.presentation.ui.customfield.CustomButton
import com.converter.wiprotest.presentation.ui.customfield.CustomOutlineText
import com.converter.wiprotest.presentation.ui.customfield.CustomText
import com.converter.wiprotest.presentation.ui.theme.WiproTestTheme
import com.converter.wiprotest.presentation.util.ResultResource
import com.converter.wiprotest.presentation.util.Validation

/**
 * Created by sathish on 01,May,2024
 */

@Composable
fun IBanScreen(navController: NavController,iBanValidationViewModel:IBanValidationViewModel = hiltViewModel())
{

    val ibanState by iBanValidationViewModel.getResultResponse.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .background(color = Color.White)
        .padding(10.dp)
        .fillMaxSize()) {
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentSize()) {

            Text(modifier = Modifier
                .wrapContentWidth()
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),text = "IBAN Validation",
                fontSize = 18.sp, color = Color.Black)

            CustomOutlineText( modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), placeholder = "Please enter IBAN Number",
                value = iBanValidationViewModel.ibanNumber.value,{
                iBanValidationViewModel.ibanNumber.value= it
            }, error = iBanValidationViewModel.ibanNumberError.value)

            CustomButton(modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),textValue = "Validate",
                onClick = {
                    if(Validation.isBanNumberValid(iBanValidationViewModel.ibanNumber.value))
                    {
                       iBanValidationViewModel.validateIban()
                    }
                    else
                    {
                        iBanValidationViewModel.ibanNumberError.value=true
                    }
                })


            ShowIbanDetail(ibanState,modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.CenterHorizontally))
        }

    }

}

@Composable
private fun ShowIbanDetail(ibanState:ResultResource<IbanModel>?,modifier: Modifier)
{

    when(ibanState)
    {
        is ResultResource.ErrorMessage -> CustomText(value = "Please Give Proper IBAN Number")
        is ResultResource.Loading -> CircularProgressIndicator()
        is ResultResource.Success -> {
            Card(modifier = modifier) {

                CustomText(modifier = Modifier.padding(10.dp),"Bank Name: ${ibanState.data!!.bankData.name}")

                CustomText(modifier = Modifier.padding(10.dp),"`Country:  ${ibanState.data.ibanData.country}")

                CustomText(modifier = Modifier.padding(10.dp),"City: ${ibanState.data.bankData.city}")

                CustomText(modifier = Modifier.padding(10.dp),"Status: ${ibanState.data.message}")

                CustomButton(modifier= Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),textValue = "Proceed",
                    onClick = {})
            }
        }
        else -> {}
    }


}

@Composable
@Preview
fun PreviewIBanScreen()
{
    WiproTestTheme {

        IBanScreen(rememberNavController())
    }
}