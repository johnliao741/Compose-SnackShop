package com.jazzhipster.snackshop.presentation.common

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.remote.model.SnackCardItem
import com.jazzhipster.snackshop.remote.model.SnackItem
import com.jazzhipster.snackshop.remote.type.SnackCardItemType
import com.jazzhipster.snackshop.ui.theme.Mask
import java.util.*
import kotlin.random.Random

@Composable
fun SnackCard(data: SnackCardItem, modifier: Modifier = Modifier.fillMaxWidth()) {
    Column(modifier = modifier.wrapContentHeight()) {
        Row(
            modifier = modifier
                .padding(vertical = 18.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                data.title,
                fontSize = 22.sp,
                color = Color(85, 77, 86),
                fontWeight = FontWeight.Bold
            )
            Image(painter = painterResource(id = R.mipmap.btn_view_more), contentDescription = null)
        }
        when (data.type) {
            SnackCardItemType.RowMaskRectangle -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    MaskImageCard(
                        modifier
                            .width(240.dp)
                            .aspectRatio(2f), snackItem)
                }

            }
            SnackCardItemType.RowSquare -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    ImageTextSnackCard(
                        data.type,
                        modifier = modifier.width(120.dp),
                        snackItem = snackItem
                    )
                }

            }
            SnackCardItemType.RowRectangle -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    ImageTextSnackCard(
                        data.type,
                        modifier = modifier.width(240.dp),
                        snackItem = snackItem
                    )
                }

            }
            SnackCardItemType.RowSquareGallery -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    SeasonalSnackCard(modifier = modifier.width(120.dp), snackItem = snackItem)
                }
            }
            SnackCardItemType.ColumnMaskRectangle -> {

                VerticalScrollView(modifier, data, false) { snackItem ->
                    MaskImageCard(
                        modifier = modifier
                            .width(240.dp)
                            .aspectRatio(2.98333f),
                        snackItem = snackItem
                    )
                }
            }
        }

    }
}

@Composable
fun HorizontalScrollView(
    modifier: Modifier,
    data: SnackCardItem,
    isScroll: Boolean = false,
    widget: @Composable (SnackItem) -> Unit
) {
    var modifier = modifier
    if (isScroll)
        modifier = modifier.horizontalScroll(rememberScrollState())
    modifier = modifier.padding(horizontal = 16.dp)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        val displays = data.display
        displays.forEach { snackItem ->
            widget(snackItem)
        }
    }
}

@Composable
fun VerticalScrollView(
    modifier: Modifier,
    data: SnackCardItem,
    isScroll: Boolean = false,
    widget: @Composable (SnackItem) -> Unit
) {
    var modifier = modifier
    if (isScroll)
        modifier = modifier.verticalScroll(rememberScrollState())
    modifier = modifier.padding(horizontal = 16.dp)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        val displays = data.display
        displays.forEach { snackItem ->
            widget(snackItem)
        }
    }
}

@Composable
fun MaskImageCard(modifier: Modifier, snackItem: SnackItem) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
    ) {
        AsyncImage(
            model = snackItem.urls[Random.nextInt(snackItem.urls.size)],
            contentDescription = snackItem.name,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Mask)
                .alpha(0.3f)
        )
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                snackItem.maskText ?: "Quest",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }

    }
}

@Preview
@Composable
fun PreviewMaskImageCard() {
    MaskImageCard(
        Modifier.fillMaxWidth(),
        SnackItem(0, "snack", 5.0, Date().time, listOf(), "Question 1")
    )
}

@Composable
fun ImageTextSnackCard(type: SnackCardItemType, modifier: Modifier, snackItem: SnackItem) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = snackItem.urls[Random.nextInt(snackItem.urls.size)],
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .aspectRatio(if (type == SnackCardItemType.RowSquare) 1f else 2f)
                .background(
                    Brush.verticalGradient(
                        if (type == SnackCardItemType.RowSquare) listOf(
                            Color.Yellow,
                            Color.LightGray
                        )
                        else listOf(Color.Green, Color.LightGray)

                    )
                )

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = snackItem.name,
            fontWeight = FontWeight.Bold,
            color = Color(85, 77, 86)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            IconText(R.mipmap.rating, snackItem.rating.toString())
            IconText(R.mipmap.time, snackItem.beforeTime())
        }
    }
}

@Composable
fun IconText(
    iconRes: Int? = null,
    text: String? = null,
    annotationText :AnnotatedString? = null,
    parentModifier :Modifier = Modifier.wrapContentWidth(),
    iconSize : Dp = 12.dp,
    textColor:Color = Color.Black,
    fontSize :TextUnit = 12.sp
) {
    Row(modifier = parentModifier, verticalAlignment = Alignment.CenterVertically) {
        if(iconRes != null){
            Image(
                painter = painterResource(id = iconRes), contentDescription = "rating",
                modifier = Modifier
                    .width(iconSize)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(3.dp))
        }
        text?.run {
            Text(text, color = textColor, fontSize = fontSize)
        }
        annotationText?.run {
            Text(text = annotationText)
        }
    }
}

@Composable
fun SeasonalSnackCard(modifier: Modifier, snackItem: SnackItem) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        SeasonalSnackGallery(snackItem)
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            IconText(R.mipmap.rating, snackItem.rating.toString())
            IconText(R.mipmap.time, snackItem.beforeTime())
        }
    }
}

@Composable
fun SeasonalSnackGallery(snackItem: SnackItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Cyan, RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(3.dp)
    ) {
        SquareImageCell(
            snackItem.urls[0],
            Modifier
                .align(Alignment.TopStart)
                .padding(end = 1.dp, bottom = 1.dp)
        )
        SquareImageCell(
            snackItem.urls[1],
            Modifier
                .align(Alignment.TopEnd)
                .padding(start = 1.dp, bottom = 1.dp)
        )
        SquareImageCell(
            snackItem.urls[2],
            Modifier
                .align(Alignment.BottomStart)
                .padding(end = 1.dp, top = 1.dp)
        )
        SquareImageCell(
            snackItem.urls[3],
            Modifier
                .align(Alignment.BottomEnd)
                .padding(start = 1.dp, top = 1.dp)
        )
    }
}

@Composable
fun SquareImageCell(img: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
    ) {
        AsyncImage(
            model = img,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "img"
        )
    }

}