package com.jazzhipster.snackshop.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.VerticalLine
import com.jazzhipster.snackshop.presentation.login.type.LoginType
import com.jazzhipster.snackshop.presentation.login.type.SocialLogin
import com.jazzhipster.snackshop.presentation.login.type.SocialLogin.*
import com.jazzhipster.snackshop.ui.theme.Blue
import com.jazzhipster.snackshop.ui.theme.LighterGray

@Composable
fun MainLoginPage(
    navAction: (LoginType) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (background, appIcon, fbBtn, googleBtn, appleBtn, line, emailBtn) = createRefs()
        val marginTop = createGuidelineFromTop(0.1954f)
        Image(painter = painterResource(id = R.mipmap.main_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.8f)
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = R.mipmap.app_logo),
            contentDescription = "app icon",
            modifier = Modifier
                .width(140.dp)
                .aspectRatio(1.152f)
                .constrainAs(appIcon) {
                    top.linkTo(marginTop)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        SocialButton(
            {type->navAction(type)},
            socialLogin = Facebook,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(fbBtn) {
                    bottom.linkTo(googleBtn.top, 12.dp)
                }
        )
        SocialButton(
            {type->navAction(type)},
            socialLogin = Google,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(googleBtn) {
                    bottom.linkTo(appleBtn.top, 12.dp)
                }
        )
        SocialButton(
            {type->navAction(type)},
            socialLogin = Apple,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(appleBtn) {
                    bottom.linkTo(line.top, 16.dp)
                }
        )
        VerticalLine(modifier = Modifier.padding(horizontal = 80.dp, vertical = 12.dp)
            .background(Color.White)
            .alpha(0.3f)
            .constrainAs(line) {
                bottom.linkTo(emailBtn.top, 16.dp)
            }
            .fillMaxWidth())

        TextButton(
            onClick = { navAction(LoginType.Email)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White)
                .border(
                    1.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RectangleShape
                )
                .constrainAs(emailBtn) {
                    bottom.linkTo(parent.bottom, 37.dp)
                }
        ) {
            Text(
                text = "Sign up with Email",
                color = MaterialTheme.colors.primary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold

            )
        }
    }
}

@Preview
@Composable
fun PreviewMainLoginPage() {
    MainLoginPage(navAction = { })
}

@Composable
fun SocialButton(
    navAction: (LoginType) -> Unit,
    socialLogin: SocialLogin,
    modifier: Modifier
) {
    val textColor = when (socialLogin) {
        is Facebook -> Color.White
        is Google -> Color.Gray
        is Apple -> Color.White
    }
    val backgroundColor = when (socialLogin) {
        is Facebook -> Blue
        is Google -> Color.White
        is Apple -> Color.Black
    }
    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navAction(LoginType.Social)
            }

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = socialLogin.iconRes),
                contentDescription = "social icon",
                modifier = Modifier
                    .width(24.dp)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                "Sign in with ${socialLogin.name}",
                color = textColor,
                fontSize = 17.sp,
                fontWeight = if (socialLogin !is Apple) FontWeight.Bold
                else null
            )
        }
    }
}

