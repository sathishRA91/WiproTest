package com.converter.wiprotest.presentation.ui.customfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by sathish on 01,May,2024
 */

@Composable
fun CustomButton(modifier: Modifier = Modifier,onClick:() -> Unit = {},textValue:String)
{

    Button(
        onClick = {onClick()},
        modifier=modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Blue)
    ) {
        Text(
            text = textValue,
            fontSize = 14.sp,
        )
    }
}