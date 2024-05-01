package com.converter.wiprotest.presentation.ui.customfield

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Created by sathish on 01,May,2024
 */


@Composable
fun CustomText(modifier: Modifier = Modifier,value:String,size:TextUnit=12.sp,color:Color= Color.Black)
{
    Text(modifier = modifier,text =value, fontSize = size)
}