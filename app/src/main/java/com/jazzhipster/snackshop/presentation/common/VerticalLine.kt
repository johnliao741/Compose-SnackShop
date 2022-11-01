package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jazzhipster.snackshop.ui.theme.LightGray

@Composable
fun VerticalLine(modifier:Modifier){
    Box(
        modifier = modifier
            .height(1.dp)
    )
}