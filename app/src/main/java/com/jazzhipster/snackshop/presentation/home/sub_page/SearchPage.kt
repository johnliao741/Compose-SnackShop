package com.jazzhipster.snackshop.presentation.home.sub_page

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.presentation.common.IconText
import com.jazzhipster.snackshop.presentation.common.MyAppBar
import com.jazzhipster.snackshop.presentation.common.ShowView
import com.jazzhipster.snackshop.presentation.common.VerticalLine
import com.jazzhipster.snackshop.remote.model.GetSearchListResponse
import com.jazzhipster.snackshop.remote.model.SearchResultType
import com.jazzhipster.snackshop.ui.theme.Gray
import com.jazzhipster.snackshop.ui.theme.LightGray
import com.jazzhipster.snackshop.ui.theme.LighterGray


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchPage(
    modifier: Modifier,
    backAction: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        searchViewModel.getSearchList("", true)
    }
    val searchList = searchViewModel.getSearchListLiveData.observeAsState()

    ShowView(state = searchList) { data ->
        Scaffold(
            topBar = {
                MyAppBar(
                    backAction = {},
                    showBackIcon = false,
                    showSearchBar = true,
                    searchChangeAction = {search->
                        searchViewModel.getSearchList(search)
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                when (data.type) {
                    SearchResultType.DEFAULT -> {
                        SearchDefaultCards(data)

                    }
                    SearchResultType.SEARCH -> {
                        if (data.suggests.isNotEmpty())
                            SearchResultCards(data)
                        else
                            NoSearchResult()
                    }
                }
            }
        }
    }


}

fun LazyListScope.NoSearchResult() {
    item {

        Column(verticalArrangement = Arrangement.spacedBy(17.dp)) {
            Text("Search result", color = Gray, fontWeight = FontWeight.Bold)

            IconText(
                iconRes = R.mipmap.warn, iconSize = 24.dp,
                textColor = LightGray, fontSize = 17.sp,
                annotationText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = LightGray, fontSize = 17.sp)){
                        append("The item you are looking for is not available in our library. ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontSize = 17.sp)) {
                        append("Try something like Baklava.")
                    }
                }
            )
            VerticalLine(modifier = Modifier
                .fillMaxWidth()
                .background(LightGray))
            Image(
                painter = painterResource(id = R.mipmap.variant1),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
        }
    }
}

fun LazyListScope.SearchResultCards(data: GetSearchListResponse) {
    item {
        Text("Search result", color = Gray, fontWeight = FontWeight.Bold)
    }
    data.suggests.forEach {
        item {
            Column {
                IconText(
                    text = it,
                    textColor = LightGray, fontSize = 17.sp,
                    parentModifier = Modifier
                        .clickable {
                            Log.e("click", it)
                        }
                        .padding(vertical = 17.dp)
                        .fillMaxWidth()
                )
                VerticalLine(modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGray))
            }
        }
    }
}

fun LazyListScope.SearchDefaultCards(data: GetSearchListResponse) {
    if (data.recentSearches.isNotEmpty()) {
        item {
            Text("Recent searches", color = Gray, fontWeight = FontWeight.Bold)
        }
        data.recentSearches.forEach {
            item {
                Column {
                    IconText(
                        iconRes = R.mipmap.recent, text = it,
                        iconSize = 24.dp,
                        textColor = LightGray, fontSize = 17.sp,
                        parentModifier = Modifier
                            .clickable {
                                Log.e("click", it)
                            }
                            .padding(vertical = 17.dp)
                            .fillMaxWidth()

                    )
                    VerticalLine(modifier = Modifier
                        .fillMaxWidth()
                        .background(LightGray))
                }

            }
        }
    }
    if (data.suggests.isNotEmpty()) {
        item {
            Text("Suggested", color = Gray, fontWeight = FontWeight.Bold)
        }
        data.suggests.forEach {
            item {
                Column {
                    IconText(
                        iconRes = R.mipmap.suggest, text = it,
                        iconSize = 24.dp,
                        textColor = LightGray, fontSize = 17.sp,
                        parentModifier = Modifier
                            .clickable {
                                Log.e("click", it)
                            }
                            .padding(vertical = 17.dp)
                            .fillMaxWidth()

                    )
                    VerticalLine(modifier = Modifier
                        .fillMaxWidth()
                        .background(LightGray))
                }

            }
        }
    }
}
