package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.ui.theme.LighterGray

@Composable
fun MyAppBar(
    showBackIcon: Boolean = false,
    backAction: () -> Unit,
    showSearchBar: Boolean = false,
    searchChangeAction: ((String) -> Unit)? = null,
    title: String? = null,
    showTrailIcon: Boolean = false,
    trailIconAction:(()->Unit)? = null,
    trailIconRes:Int?= null
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(
        Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .aspectRatio(9.9444f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackIcon)
            IconButton(onClick = { backAction() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black

                )
            }
        if(showSearchBar){
            Row(
                Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth()
                    .aspectRatio(9.9444f)
                    .background(
                        LighterGray
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.search),
                    contentDescription = "search"
                )
                Spacer(modifier = Modifier.width(5.dp))
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = {
                        textFieldValue = it
                        searchChangeAction?.invoke(textFieldValue.text)

                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 17.sp, textAlign = TextAlign.Start),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    maxLines = 1,
                    visualTransformation = VisualTransformation.None,
                )
            }
        }else{
            title?.run{
                Text(title)
            }
        }
        if(showTrailIcon){
            IconButton(onClick = { trailIconAction?.invoke() }) {
                Icon(painter = painterResource(id = trailIconRes!!),contentDescription = null)
            }
        }

    }
}