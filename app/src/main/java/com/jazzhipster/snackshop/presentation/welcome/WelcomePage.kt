package com.jazzhipster.snackshop.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.MyTextButton
import com.jazzhipster.snackshop.ui.theme.Orange700

@ExperimentalPagerApi
@Composable
fun WelcomePage(
    navAction: () -> Unit,
    modifier: Modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (horizontalPager, indicator, button) = createRefs()
        val imagePaths = listOf<Int>(R.mipmap.variant1, R.mipmap.variant2, R.mipmap.variant3)
        val showTexts = listOf<String>(
            "Hand-pickle high quality snacks.",
            "Shop global. Mind-blownly affordable.",
            "Deliver on the promise of time."
        )
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = imagePaths.size,
            state = pagerState,
            modifier = Modifier.constrainAs(horizontalPager) {
                top.linkTo(parent.top)
                bottom.linkTo(button.top)
            }
        ) { index ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imagePaths[index]),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = showTexts[index],
                    color = MaterialTheme.colors.primary,
                    fontSize = 34.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .constrainAs(indicator) {
                    top.linkTo(horizontalPager.bottom)
                    bottom.linkTo(button.top)
                }
        )
        MyTextButton(
            navAction = {
                navAction()
            },
            enable = pagerState.currentPage == imagePaths.lastIndex,
            modifier = Modifier
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(button) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)

            },
            text = "Get started",
            textColor = Color.White
        )


    }
}
@ExperimentalPagerApi
@Preview
@Composable
fun PreviewWelcomePage(){
    WelcomePage(navAction = {})
}