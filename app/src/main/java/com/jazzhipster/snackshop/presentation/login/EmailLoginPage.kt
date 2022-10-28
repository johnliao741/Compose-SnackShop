package com.jazzhipster.snackshop.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.MyTextButton
import com.jazzhipster.snackshop.ui.theme.Gray

@Composable
fun EmailLoginPage(
    navAction: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize().background(Color.White)
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    ConstraintLayout(modifier = modifier.padding(horizontal = 16.dp)) {
        val (icon, welcomeText, emailTextField, passwordField, line, signUpBtn, privacyPolicy) = createRefs()
        Image(
            painter = painterResource(id = R.mipmap.email_login_icon),
            contentDescription = "email login icon",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3.1707f)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 102.dp)
                    bottom.linkTo(welcomeText.top)
                }
        )
        Text(text = "Welcome back!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Gray,
            modifier = Modifier.fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .constrainAs(welcomeText) {
                top.linkTo(icon.bottom)
                bottom.linkTo(emailTextField.top)
            }
        )
        LoginTextField(
            iconRes = R.mipmap.email,
            hintText = "Please input email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textFieldValue = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(emailTextField) {
                    top.linkTo(welcomeText.bottom)
                    bottom.linkTo(passwordField.top)
                }
        )
        LoginTextField(
            iconRes = R.mipmap.password,
            hintText = "Please input password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textFieldValue = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(passwordField) {
                    top.linkTo(emailTextField.bottom)
                    bottom.linkTo(line.top)
                }
        )
        Box(modifier = Modifier
            .padding(horizontal = 64.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
            .alpha(0.3f)
            .constrainAs(line) {
                top.linkTo(passwordField.bottom)
                bottom.linkTo(signUpBtn.top)
            }
        )
        MyTextButton(
            navAction = { navAction() },
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .fillMaxWidth()
                .constrainAs(signUpBtn){
                 top.linkTo(line.bottom)
                bottom.linkTo(privacyPolicy.top)
            },
            text = "Create account",
            textColor = Color.White
        )

        AnnotatedClickableText(modifier = Modifier.constrainAs(privacyPolicy){
            top.linkTo(signUpBtn.bottom)
            bottom.linkTo(parent.bottom,114.dp)
        })
    }

}
@Preview
@Composable
fun PreviewEmailLoginPage(){
    EmailLoginPage(navAction = { })
}
@Composable
fun LoginTextField(
    iconRes: Int?,
    hintText: String = "",
    fontSize: TextUnit = 17.sp,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    val focus = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .wrapContentHeight()
            .background(
                if (focus.value) Color.White
                else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                1.dp,
                if (focus.value) Color.LightGray
                else Color.White,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconRes != null) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = "",
                    modifier = Modifier
                        .width(24.dp)
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            BasicTextField(
                value = textFieldValue,
                textStyle = TextStyle(fontSize = fontSize),
                onValueChange = { onValueChange(it) },
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        focus.value = focusState.isFocused
                    }
            ) { innerTextField ->
                if (textFieldValue.text.isEmpty())
                    Text(
                        text = hintText,
                        fontSize = fontSize,
                        color = if (focus.value) Color.LightGray
                        else Color.White,
                        modifier = Modifier.alpha(0.8f)
                    )
                innerTextField()
            }
        }
    }

}

@Composable
fun AnnotatedClickableText(
    modifier: Modifier,
    fontSize:TextUnit = 12.sp
) {
    val annotatedText = buildAnnotatedString {
        append("By clicking \"Create account\", I agree to SnackOverflow’s  and Privacy Policy.")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "TOS",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)
        ) {
            append("TOS")
        }
        pop()
        append(" and ")
        pushStringAnnotation(tag = "Privacy Policy",
            annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue,
            fontWeight = FontWeight.Bold)
        ) {
            append("Privacy Policy")
        }
        pop()
        append(".")
    }

    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = TextStyle(fontSize = fontSize),
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "TOS", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Log.d("Clicked TOS", annotation.item)
                }
            annotatedText.getStringAnnotations(tag = "Privacy Policy", start = offset,
                end = offset)
                .firstOrNull()?.let { annotation ->
                    // If yes, we log its value
                    Log.d("Clicked Privacy Policy", annotation.item)
                }
        }
    )
}