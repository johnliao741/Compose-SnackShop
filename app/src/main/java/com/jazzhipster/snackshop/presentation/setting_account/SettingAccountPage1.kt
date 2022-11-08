package com.jazzhipster.snackshop.presentation.setting_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.IconText
import com.jazzhipster.snackshop.presentation.common.MyIconButton
import com.jazzhipster.snackshop.presentation.common.StepBarView
import com.jazzhipster.snackshop.remote.model.SettingUserAccountRequest
import com.jazzhipster.snackshop.remote.model.SnackGoalType
import com.jazzhipster.snackshop.ui.theme.LightGray
import com.jazzhipster.snackshop.ui.theme.LighterGray
import com.jazzhipster.snackshop.ui.theme.Yellow

@Composable
fun SettingAccountPage1(
    viewModel: SettingAccountPage1ViewModel = hiltViewModel(),
    navAction: (SettingUserAccountRequest) -> Unit
) {
    val settingUserAccount by viewModel.settingUserAccount.observeAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StepBarView(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .aspectRatio(10.2631f),
                0,
                4,
                currentColor = Yellow,
                color = LightGray
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "What is your name?",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.height(72.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.30769f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(60.dp))
                    .background(LighterGray),
                painter = painterResource(
                    id = R.mipmap.head_photo
                ),
                contentDescription = "head photo",
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "user02398423",
                fontSize = 17.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "x height",
                    color = LightGray,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "x weight",
                    color = LightGray,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            IconText(
                iconRes = R.mipmap.location_gray,
                text = "unknown",
                iconSize = 24.dp,
                textColor = LightGray,
                fontSize = 15.sp

            )
            Spacer(modifier = Modifier.height(42.dp))
            MyIconButton(
                clickAction = {
//                    settingUserAccount?.run {
//                        navAction(this)
//                    }
                    navAction(SettingUserAccountRequest(
                        img = null,
                        name = "John",
                        height = 180.0,
                        weight = 60.0,
                        location = "新北市",
                        goal = SnackGoalType.Nothing,
                        selectSnackId = listOf()
                    ))


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(6.39285f),
                text = "Edit",
                textColor = MaterialTheme.colors.primary,
                iconRes = R.mipmap.edit,
                iconColor = MaterialTheme.colors.primary,
                background = Color.LightGray
            )

        }
    }
}

@Preview
@Composable
fun PreviewSettingAccountPage1() {
    SettingAccountPage1() {

    }
}