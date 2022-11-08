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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.*
import com.jazzhipster.snackshop.remote.model.SettingUserAccountRequest
import com.jazzhipster.snackshop.remote.model.SnackGoalType
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.ui.theme.LightGray
import com.jazzhipster.snackshop.ui.theme.LighterGray
import com.jazzhipster.snackshop.ui.theme.Yellow

@Composable
fun SettingAccountPage2(
    viewModel: SettingAccountPage2ViewModel = hiltViewModel(),
    settingUserAccount: SettingUserAccountRequest,
    navAction: (SettingUserAccountRequest) -> Unit,
    backAction: () -> Unit
) {
    val goalOptions by viewModel.userGoals.observeAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StepBarView(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .aspectRatio(10.2631f),
                1,
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
                text = "What is your goal?",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.height(72.dp))
            goalOptions?.forEachIndexed { index, text ->
                MyRadioButton(
                    currentIndex = index,
                    size = goalOptions!!.size,
                    text = text,
                    fontSize = 17.sp,
                    textColor = LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(6.39285f)
                        .clip(RoundedCornerShape(15.dp))
                        .background(LighterGray)
                )
                if (index != goalOptions?.lastIndex)
                    Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyTextButton(
                    clickAction = {
                        navAction(settingUserAccount)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(6.39285f),
                    text = "Continue",
                    textColor = Color.White,
                    background = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                MyTextButton(
                    clickAction = {
                        backAction()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(6.39285f),
                    text = "Back",
                    textColor = MaterialTheme.colors.primary,
                    background = LightGray
                )
            }

        }
    }
}

//@Preview
//@Composable
//fun PreviewSettingAccountPage2() {
//    SettingAccountPage2(
//        settingUserAccount = SettingUserAccountRequest(
//            img = "",
//            name = "",
//            height = 0.0,
//            weight = 0.0,
//            location = "",
//            goal = SnackGoalType.NoGoal,
//            selectSnackId = listOf()
//        ),
//        navAction = {
//
//        },
//        backAction = {
//
//        }
//    )
//}