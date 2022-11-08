package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.ui.theme.LightGray
import com.jazzhipster.snackshop.ui.theme.LighterGray

@Composable
fun MyRadioButton(
    currentIndex: Int,
    size: Int,
    text: String,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = MaterialTheme.colors.primary,
    selectedIconRes:Int = R.mipmap.selected,
    unSelectIconRes:Int = R.mipmap.unselect,
    modifier: Modifier,

    ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconRes = if (currentIndex == size - 1) selectedIconRes else unSelectIconRes
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "Radio img",
            modifier = Modifier
                .width(24.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(13.dp))
        Text(text, color = textColor, fontSize = fontSize, fontWeight = fontWeight)
    }
}

@Preview
@Composable
fun PreviewMyRadioButton() {
    MyRadioButton(
        currentIndex = 2,
        size = 3,
        text = "select",
        fontSize = 17.sp,
        textColor = LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(6.39285f)
            .clip(RoundedCornerShape(15.dp))
            .background(LighterGray)


    )
}