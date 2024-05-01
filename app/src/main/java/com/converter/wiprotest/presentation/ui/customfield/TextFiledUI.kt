package com.converter.wiprotest.presentation.ui.customfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Created by sathish on 30,April,2024
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlineText(modifier: Modifier = Modifier,placeholder:String,value:String,onTextChange:(String) -> Unit,
                      enable:Boolean=true,
                      error:Boolean=false,
                      trailingIcon:@Composable() ()->Unit={},
                      onClick:() -> Unit = {})
{
    OutlinedTextField(
        modifier = modifier.clickable { onClick() },
        value = value, onValueChange = {onTextChange(it)},
        textStyle = TextStyle.Default.copy(color = Color.Black),
        shape = RoundedCornerShape(10.dp),
        enabled = enable,
        isError = error,
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        trailingIcon = { trailingIcon()},
        singleLine = true)
}



