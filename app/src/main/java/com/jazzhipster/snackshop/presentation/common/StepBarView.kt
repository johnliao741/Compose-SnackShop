package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jazzhipster.snackshop.ui.theme.LightGray

@Composable
fun StepBarView(
    parentModifier: Modifier,
    index: Int,
    size: Int,
    currentColor:Color,
    color: Color
) {
    Row(
        modifier = parentModifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        (0 until size).forEachIndexed { i, value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .weight(1f)
                    .background(
                        if (i == index)
                            currentColor
                        else color
                    )
            )
        }
    }
}

@Preview
@Composable
fun PreviewStepBarView() {
    StepBarView(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        index = 0,
        size = 4,
        currentColor = MaterialTheme.colors.primary,
        color = Color.LightGray

    )
}