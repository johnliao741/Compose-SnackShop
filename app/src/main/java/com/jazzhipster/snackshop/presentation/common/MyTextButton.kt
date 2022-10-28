package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyTextButton(
    navAction:()->Unit,
    enable:Boolean = true,
    modifier: Modifier,
    text:String,
    textColor:Color,
    fontSize:TextUnit = 17.sp
){
    TextButton(
        onClick = {
            navAction()
        },
        enabled = enable,
        modifier = modifier
            .background(
                color = if (enable) MaterialTheme.colors.primary
                else Color.Gray, shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(text =text, color = textColor, fontSize = fontSize)
    }
}