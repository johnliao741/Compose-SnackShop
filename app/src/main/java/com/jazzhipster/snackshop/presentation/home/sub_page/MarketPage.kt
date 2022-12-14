package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel

import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.MyAppBar

import com.jazzhipster.snackshop.presentation.common.ShowView
import com.jazzhipster.snackshop.presentation.common.SnackCard
import com.jazzhipster.snackshop.ui.theme.LighterGray


@Composable
fun MarketPage(modifier: Modifier,
               viewModel: MarketViewModel = hiltViewModel(),
               clickAction:(String) ->Unit,
               searchAction:()->Unit){
    LaunchedEffect(key1 = true){
        viewModel.getData()
    }
    val result = viewModel.getDataLiveData.observeAsState()
    ShowView(result){data->
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                MyAppBar(
                    backAction = {},
                    showBackIcon = false,
                    showSearchBar = true,
                    clickAction = searchAction
                )
            }
        ) { paddingValues->
            LazyColumn(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()){
                data.category.forEach{item->
                    item {
                        SnackCard(data = item,clickAction = clickAction)
                    }
                }
            }
        }

    }
}