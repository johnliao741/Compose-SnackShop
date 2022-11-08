package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil.compose.AsyncImage
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.*
import com.jazzhipster.snackshop.remote.model.beforeTime
import com.jazzhipster.snackshop.remote.result.Result
import com.jazzhipster.snackshop.ui.theme.Gray
import com.jazzhipster.snackshop.ui.theme.LightGray

@ExperimentalLifecycleComposeApi
@Composable
fun SearchResultDetailPage(
    modifier: Modifier,
    snackId: String,
    viewModel: SearchResultDetailViewModel = hiltViewModel(),
    backAction:()->Unit,
    clickAction: (String) -> Unit
) {

    val snackDetail = viewModel.getSnackDetailLiveData.observeAsState()
    val addShoppingCart = viewModel.addShoppingCartFlow.collectAsStateWithLifecycle(Result.Initial)
    val saveWishList = viewModel.saveWishListFlow.collectAsStateWithLifecycle(Result.Initial)
    LaunchedEffect(key1 = true) {
        viewModel.getSnackDetail(snackId)
    }
    ShowView(state = addShoppingCart) {
        val dialogState: MutableState<Boolean> = remember {
            mutableStateOf(true)
        }
        if(dialogState.value){
            AlertDialog(
                onDismissRequest = { dialogState.value = false },
                title = {
                    Box(Modifier.fillMaxWidth())
                },
                text = {
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "Success", color = Color.Black)
                },
                buttons = {
                    TextButton(modifier = Modifier.fillMaxWidth(),onClick = { dialogState.value = false }) {
                        Text(text = "Ok")
                    }
                },
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.White
            )
        }

    }
    ShowView(state = saveWishList) {
        val dialogState: MutableState<Boolean> = remember {
            mutableStateOf(true)
        }
        if(dialogState.value){
            AlertDialog(
                onDismissRequest = { dialogState.value = false },
                title = {
                        Box(Modifier.fillMaxWidth())
                },
                text = {
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = "Success", color = Color.Black)
                },
                buttons = {
                    TextButton(modifier = Modifier.fillMaxWidth(),onClick = { dialogState.value = false }) {
                        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = "Ok")
                    }
                },
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.White
            )
        }
    }
    ShowView(state = snackDetail) { data ->
        Scaffold(
            topBar = {
                MyAppBar(
                    backAction = { backAction()},
                    showBackIcon = true,
                    title = "Search result detail",
                    horizontalPadding = 0.dp
                )
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = data.img, contentDescription = "snack img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4444f),
                    contentScale = ContentScale.Fit

                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "$${data.price}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        IconText(
                            iconRes = R.mipmap.rating,
                            text = data.rating.toString(),
                            textColor = LightGray
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        IconText(
                            iconRes = R.mipmap.time,
                            text = data.time.beforeTime(),
                            textColor = LightGray
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = data.description, color = LightGray)
                }
                Spacer(modifier = Modifier.height(24.dp))
                SnackCard(data = data.recommended, clickAction = clickAction)
                Spacer(modifier = Modifier.height(24.dp))
                MyIconButton(
                    clickAction = {
                        viewModel.addShoppingCart(snackId)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .aspectRatio(6.3928f),
                    text = "Add to shopping cart",
                    textColor = Color.White,
                    iconRes = R.mipmap.shopping_car,
                    iconColor = Color.White,
                    background = MaterialTheme.colors.primary,
                    textWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                MyIconButton(
                    clickAction = {
                        viewModel.saveWishList(snackId)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .aspectRatio(6.3928f),
                    text = "Save to wishlist",
                    textColor = MaterialTheme.colors.primary,
                    iconRes = R.mipmap.wishlist_mark,
                    iconColor = MaterialTheme.colors.primary,
                    background = Color(  red = 237, green = 236 ,blue =237),
                    textWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


}