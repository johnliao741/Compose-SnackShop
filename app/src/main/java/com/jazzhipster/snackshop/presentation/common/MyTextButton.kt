package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.ui.theme.LightGray

@Composable
fun MyTextButton(
    clickAction: () -> Unit,
    enable: Boolean = true,
    modifier: Modifier,
    text: String,
    textColor: Color,
    fontSize: TextUnit = 17.sp,
    background: Color = MaterialTheme.colors.primary
) {
    TextButton(
        onClick = {
            clickAction()
        },
        enabled = enable,
        modifier = modifier
            .background(
                color = if (enable) background
                else Color.Gray, shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(text = text, color = textColor, fontSize = fontSize)
    }

}

@Composable
fun MyIconButton(
    clickAction: () -> Unit,
    enable: Boolean = true,
    modifier: Modifier,
    text: String,
    textColor: Color,
    fontSize: TextUnit = 17.sp,
    textWeight :FontWeight = FontWeight.Normal,
    iconRes: Int,
    iconColor:Color,
    background:Color,
    shape: Shape = RoundedCornerShape(8.dp)
) {

    Button(
        onClick = { clickAction() },
        modifier = modifier,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(background),
        shape = shape

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(background,shape)
                ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = iconRes),
                contentDescription = "icon",
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text, color = textColor, fontWeight = textWeight, fontSize = fontSize)
        }
    }
}

@Preview
@Composable
fun previewMyIconButton() {
    MyIconButton(
        clickAction = {  },
        modifier = Modifier.fillMaxWidth(),
        text ="Click",
        textColor = MaterialTheme.colors.primary,
        iconRes = R.mipmap.wishlist_mark,
        iconColor = MaterialTheme.colors.primary,
        background = LightGray
    )
}

@Preview
@Composable
fun PreviewMyTextButton() {
    MyTextButton(
        clickAction = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        text = "Click",
        textColor = Color.White
    )
}