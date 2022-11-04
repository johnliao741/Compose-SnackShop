package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.ShowView
import com.jazzhipster.snackshop.presentation.common.SnackCard
import com.jazzhipster.snackshop.remote.model.GetSubHomeTitleResponse
import kotlin.random.Random

@Composable
fun SubHomePage(modifier: Modifier, viewModel: SubHomeViewModel = hiltViewModel(),clickAction:(String)->Unit) {
    LaunchedEffect(key1 = true) {
        viewModel.getData()
    }
    val result = viewModel.getDataLiveData.observeAsState()
    ShowView(result) { data ->
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            val titleData = data.first
            item {
                SubHomeHeader(data = titleData)
            }
            val contentData = data.second
            contentData.category.forEach { subHomeItem ->
                item {
                    SnackCard(data = subHomeItem, modifier = Modifier.fillMaxWidth(), clickAction = clickAction)
                }
            }
            item {
                Spacer(Modifier.height(16.dp))
            }
        }
    }


}

@Composable
fun SubHomeHeader(data: GetSubHomeTitleResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.44444f)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black,
                            Color.Black,
                            Color.Black,
                            Color.LightGray
                        ),
                        tileMode = TileMode.Mirror
                    )
                )
        ) {
            AsyncImage(
                model = data.url,
                contentDescription = "image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.mipmap.location),
                        contentDescription = "location",
                        modifier = Modifier
                            .width(24.dp)
                            .aspectRatio(1f)
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Text(text = data.location, color = Color.White, fontSize = 15.sp)
                }
                Text(
                    text = data.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp
                )
            }


        }
    }
}

