package com.jazzhipster.snackshop.remote.mock

import android.util.Log
import com.jazzhipster.snackshop.remote.model.*
import com.jazzhipster.snackshop.remote.service.SnackShopService
import com.jazzhipster.snackshop.remote.type.SnackCardItemType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class SnackShopServiceImp() : SnackShopService {
    override suspend fun getSubHomeTitle(request: BaseRequest): GetSubHomeTitleResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            GetSubHomeTitleResponse(
                title = "Good afternoon.\nTake a break from work",
                location = "San Francisco,CA",
                url = "https://www.lifepng.com/wp-content/uploads/2020/12/Cup-Of-Coffee-and-Beans-png-hd.png"
            )
        }

    override suspend fun getSubHomeList(request: GetSubHomeListRequest): GetListResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            GetListResponse(
                listOf(
                    SnackCardItem(
                        type = SnackCardItemType.RowMaskRectangle,
                        title = "Daily quests",
                        display = List(4) {
                            SnackItem(
                                id = it,
                                name = "Snack $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://w7.pngwing.com/pngs/965/299/png-transparent-ice-cream-creme-caramel-frutti-di-bosco-cheesecake-dessert-assorted-desserts-cream-frutti-di-bosco-food-thumbnail.png",
                                    "https://w7.pngwing.com/pngs/499/680/png-transparent-brown-and-pink-cupcake-with-strawberry-dessert-watercolor-painting-drawing-illustration-hand-painted-dessert-cake-cream-frutti-di-bosco-painted.png",
                                    "https://e7.pngegg.com/pngimages/879/358/png-clipart-panna-cotta-italian-cuisine-dessert-cream-creme-caramel-dessert-frutti-di-bosco-food.png",
                                    "https://www.pngall.com/wp-content/uploads/7/Dessert-PNG-Photo.png"
                                ),
                                maskText = "Question : ${it + 1}"
                            )
                        }
                    ),
                    SnackCardItem(
                        type = SnackCardItemType.RowSquare,
                        title = "Popular items",
                        display = List(5) {
                            SnackItem(
                                id = it,
                                name = "Dessert $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://image.shutterstock.com/image-photo/savory-party-snack-selection-white-260nw-185699627.jpg",
                                    "https://purepng.com/public/uploads/thumbnail//popcorn-in-jar-s8o.png",
                                    "https://static.vecteezy.com/system/resources/thumbnails/012/627/666/small/3d-popcorn-striped-bucket-cinema-snack-movie-entertainment-concept-high-quality-isolated-3d-render-png.png",

                                    ),
                                maskText = null
                            )
                        }
                    ),
                    SnackCardItem(
                        type = SnackCardItemType.RowRectangle,
                        title = "Recommended for you",
                        display = List(4) {
                            SnackItem(
                                id = it,
                                name = "Recommended food $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://png.pngtree.com/png-clipart/20190911/ourmid/pngtree-group-of-almonds-and-cashew-nuts-png-background-png-image_1729179.jpg",
                                    "https://www.pngall.com/wp-content/uploads/2016/06/Walnut-Free-Download-PNG.png",
                                    "https://e7.pngegg.com/pngimages/219/588/png-clipart-hawaii-had-almond-raisins-pine-nuts-macadamia-nuts-pine-nuts-thumbnail.png",
                                    "https://w7.pngwing.com/pngs/174/372/png-transparent-hazelnut-nut-dried-fruit-food-thumbnail.png"
                                ),
                                maskText = null
                            )
                        }
                    ),
                    SnackCardItem(
                        type = SnackCardItemType.RowSquareGallery,
                        title = "Seasonal bundles",
                        display = List(4) {
                            SnackItem(
                                id = it,
                                name = "seasonal food $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://img.favpng.com/22/4/17/avocado-watercolor-painting-fruit-illustration-png-favpng-avMqjxEMJefVbsn5tYhxKwBmN_t.jpg",
                                    "https://img.lovepik.com/element/40173/8513.png_300.png",
                                    "https://vaya.in/wp-content/uploads/2021/08/Figs-1.jpg",
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxcwOSqoDdHJ1W4_h5aLhRb8q2jtnvzXhArQ&usqp=CAU"
                                ),
                                maskText = null
                            )
                        }
                    ),

                    )
            )
        }

    override suspend fun getMarketList(request: BaseRequest): GetListResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            GetListResponse(
                listOf(
                    SnackCardItem(
                        type = SnackCardItemType.RowSquareGallery,
                        title = "Recent users’ bundles",
                        display = List(4) {
                            SnackItem(
                                id = it,
                                name = "Snack $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://w7.pngwing.com/pngs/965/299/png-transparent-ice-cream-creme-caramel-frutti-di-bosco-cheesecake-dessert-assorted-desserts-cream-frutti-di-bosco-food-thumbnail.png",
                                    "https://w7.pngwing.com/pngs/499/680/png-transparent-brown-and-pink-cupcake-with-strawberry-dessert-watercolor-painting-drawing-illustration-hand-painted-dessert-cake-cream-frutti-di-bosco-painted.png",
                                    "https://e7.pngegg.com/pngimages/879/358/png-clipart-panna-cotta-italian-cuisine-dessert-cream-creme-caramel-dessert-frutti-di-bosco-food.png",
                                    "https://www.pngall.com/wp-content/uploads/7/Dessert-PNG-Photo.png"
                                ),
                                maskText = "Question : ${it + 1}"
                            )
                        }
                    ),
                    SnackCardItem(
                        type = SnackCardItemType.ColumnMaskRectangle,
                        title = "Top 3 popular bundles",
                        display = List(5) {
                            SnackItem(
                                id = it,
                                name = "Dessert $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://image.shutterstock.com/image-photo/savory-party-snack-selection-white-260nw-185699627.jpg",
                                    "https://purepng.com/public/uploads/thumbnail//popcorn-in-jar-s8o.png",
                                    "https://static.vecteezy.com/system/resources/thumbnails/012/627/666/small/3d-popcorn-striped-bucket-cinema-snack-movie-entertainment-concept-high-quality-isolated-3d-render-png.png"

                                ),
                                maskText = "Snack $it bundle"
                            )
                        }
                    ),
                    SnackCardItem(
                        type = SnackCardItemType.RowSquare,
                        title = "People nearby favorites",
                        display = List(4) {
                            SnackItem(
                                id = it,
                                name = "Recommended food $it",
                                rating = Random.nextDouble(5.1).run {
                                    ((this * 100).roundToInt() / 100).toDouble()
                                },
                                timestamp = Date().time - Random.nextLong(3600000),
                                urls = listOf(
                                    "https://png.pngtree.com/png-clipart/20190911/ourmid/pngtree-group-of-almonds-and-cashew-nuts-png-background-png-image_1729179.jpg",
                                    "https://www.pngall.com/wp-content/uploads/2016/06/Walnut-Free-Download-PNG.png",
                                    "https://e7.pngegg.com/pngimages/219/588/png-clipart-hawaii-had-almond-raisins-pine-nuts-macadamia-nuts-pine-nuts-thumbnail.png",
                                    "https://w7.pngwing.com/pngs/174/372/png-transparent-hazelnut-nut-dried-fruit-food-thumbnail.png"
                                ),
                                maskText = null
                            )
                        }
                    ),
                )
            )
        }

    override suspend fun getSearchList(request: GetSearchRequest): GetSearchListResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            Log.e("search", request.query)
            GetSearchListResponse(
                recentSearches = listOf("Afternoon tea", "Potato chips", "Milk jelly"),
                suggests = when{
                    request.query.isEmpty()->{
                        listOf(
                            "Fruit jelly",
                            "Simit lovers",
                            "Japanese street food",
                            "Turkish tea time bundle",
                            "Delicious samosa bundle"
                        )
                    }
                    else->{
                        val format = Regex("[0-9a-z]+")
                        if(format.matches(request.query)){
                            List(10) {
                                "search ${request.query} -> result : ${it + 1}"
                            }
                        }else{
                            listOf()
                        }
                    }
                },
                type = if (request.query.isEmpty()) SearchResultType.DEFAULT else SearchResultType.SEARCH
            )
        }


}