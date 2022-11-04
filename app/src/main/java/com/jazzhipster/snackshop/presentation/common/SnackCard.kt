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
import com.jazzhipster.snackshop.remote.model.beforeTime
import com.jazzhipster.snackshop.remote.type.SnackCardItemType
import com.jazzhipster.snackshop.ui.theme.Gray
import com.jazzhipster.snackshop.ui.theme.LightGray
import com.jazzhipster.snackshop.ui.theme.Mask
import java.util.*
import kotlin.random.Random

@Composable
fun SnackCard(
    data: SnackCardItem,
    modifier: Modifier = Modifier.fillMaxWidth(),
    clickAction: (String) -> Unit
) {
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
                            .aspectRatio(2f), snackItem
                    ) {
                        clickAction(it)
                    }
                }

            }
            SnackCardItemType.RowSquare -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    ImageTextSnackCard(
                        data.type,
                        modifier = modifier.width(120.dp),
                        snackItem = snackItem
                    ) {
                        clickAction(it)
                    }
                }

            }
            SnackCardItemType.RowRectangle -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    ImageTextSnackCard(
                        data.type,
                        modifier = modifier.width(240.dp),
                        snackItem = snackItem
                    ) {
                        clickAction(it)
                    }
                }

            }
            SnackCardItemType.RowSquareGallery -> {
                HorizontalScrollView(modifier, data, true) { snackItem ->
                    SeasonalSnackCard(modifier = modifier.width(120.dp),
                        snackItem = snackItem) {
                        clickAction(it)
                    }
                }
            }
            SnackCardItemType.ColumnMaskRectangle -> {

                VerticalScrollView(modifier, data, false) { snackItem ->
                    MaskImageCard(
                        modifier = modifier
                            .width(240.dp)
                            .aspectRatio(2.98333f),
                        snackItem = snackItem
                    ) {
                        clickAction(it)
                    }
                }
            }
            SnackCardItemType.ColumnSquareGallery->{
                VerticalScrollView(modifier, data, false) { snackItem ->
                    SquareArticleSnackCard(modifier = modifier, snackItem = snackItem){
                        clickAction(it)
                    }
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
fun MaskImageCard(modifier: Modifier, snackItem: SnackItem, clickAction: (String) -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { clickAction(snackItem.id.toString()) }
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
        SnackItem(0, "snack",5.0,"description", 5.0, Date().time, listOf(), "Question 1")
    ){

    }
}

@Composable
fun ImageTextSnackCard(
    type: SnackCardItemType,
    modifier: Modifier,
    snackItem: SnackItem,
    clickAction: (String) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { clickAction(snackItem.id.toString()) },
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
            IconText(R.mipmap.time, snackItem.timestamp.beforeTime())
        }
    }
}

@Composable
fun IconText(
    iconRes: Int? = null,
    text: String? = null,
    annotationText: AnnotatedString? = null,
    parentModifier: Modifier = Modifier.wrapContentWidth(),
    iconSize: Dp = 12.dp,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 12.sp
) {
    Row(modifier = parentModifier, verticalAlignment = Alignment.CenterVertically) {
        if (iconRes != null) {
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
fun SeasonalSnackCard(modifier: Modifier, snackItem: SnackItem, clickAction: (String) -> Unit) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .clickable { clickAction(snackItem.id.toString()) },
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        SeasonalSnackGallery(Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Cyan, RoundedCornerShape(8.dp)),
            snackItem)
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            IconText(R.mipmap.rating, snackItem.rating.toString())
            IconText(R.mipmap.time, snackItem.timestamp.beforeTime())
        }
    }
}
@Composable
fun SquareArticleSnackCard(modifier: Modifier,snackItem: SnackItem,clickAction: (String) -> Unit){
    Row(
        modifier = modifier
            .wrapContentHeight()
            .clickable { clickAction(snackItem.id.toString()) },
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        SeasonalSnackGallery(Modifier
            .width(120.dp)
            .aspectRatio(1f)
            .background(Color.Cyan, RoundedCornerShape(8.dp)),snackItem)
        Column {
            Text(
                "${snackItem.name}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Gray
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "${snackItem.description}",
                fontSize = 15.sp,
                color = LightGray
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "$${snackItem.price}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Gray
            )
            Row {
                IconText(
                    iconRes = R.mipmap.rating,
                    text = snackItem.rating.toString(),
                    textColor = LightGray
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconText(
                    iconRes = R.mipmap.time,
                    text = snackItem.timestamp.beforeTime(),
                    textColor = LightGray
                )
            }
        }
    }
}

@Composable
fun SeasonalSnackGallery(modifier: Modifier,snackItem: SnackItem) {
    Box(
        modifier = modifier
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