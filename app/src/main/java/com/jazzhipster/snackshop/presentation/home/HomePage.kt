package com.jazzhipster.snackshop.presentation.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jazzhipster.snackshop.R
import com.jazzhipster.snackshop.navigation.Destinations
import com.jazzhipster.snackshop.presentation.home.HomeDestination.SEARCH_PAGE_ROUTE
import com.jazzhipster.snackshop.presentation.home.sub_page.*

object HomeDestination {
    const val SUBHOME_PAGE_ROUTE = "subhome_page_route"
    const val MARKET_PAGE_ROUTE = "market_page_route"
    const val SEARCH_PAGE_ROUTE = "search_page_route"
    const val CREATE_PAGE_ROUTE = "create_page_route"
    const val WISHLIST_PAGE_ROUTE = "wishlist_page_route"
    const val ACCOUNT_PAGE_ROUTE = "account_page_route"
}

enum class HomeBottomBarItems(val destination: String, val iconRes: Int, val display: String) {
    SubHome(HomeDestination.SUBHOME_PAGE_ROUTE, R.mipmap.home, "Home"),
    Market(HomeDestination.MARKET_PAGE_ROUTE, R.mipmap.market, "Market"),
    Create(HomeDestination.CREATE_PAGE_ROUTE, R.mipmap.create, "Create"),
    Wishlist(HomeDestination.WISHLIST_PAGE_ROUTE, R.mipmap.wishlist, "Wishlist"),
    Account(HomeDestination.ACCOUNT_PAGE_ROUTE, R.mipmap.account, "Account")
}

@Composable
fun HomePage(
    navAction: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {

                HomeBottomBarItems.values().forEach { tab ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == tab.destination || isSearchPageOnMarketPage(it.route,tab.destination) } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = tab.iconRes),
                                contentDescription = "tab icon",
                                modifier = Modifier
                                    .width(20.dp)
                                    .aspectRatio(1f),
                                tint = if (isSelected) MaterialTheme.colors.primary else Color.LightGray
                            )
                        },
                        label = {
                            Text(
                                tab.display,
                                color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(tab.destination) {
                                launchSingleTop = true
                            }
                        })
                }

            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(
            navController,
            startDestination = HomeBottomBarItems.SubHome.destination,
            Modifier.padding(innerPadding)
        ) {
            composable(HomeBottomBarItems.SubHome.destination) { SubHomePage(modifier) }
            composable(SEARCH_PAGE_ROUTE) {

                SearchPage(modifier, backAction = {
                    navController.popBackStack()
                })
            }
            composable(HomeBottomBarItems.Market.destination) {
                MarketPage(modifier) {
                    navController.navigate(SEARCH_PAGE_ROUTE)
                }
            }

            composable(HomeBottomBarItems.Create.destination) { CreatePage(modifier) }
            composable(HomeBottomBarItems.Wishlist.destination) { WishlistPage(modifier) }
            composable(HomeBottomBarItems.Account.destination) { AccountPage(modifier) }
        }

    }
}

fun isSearchPageOnMarketPage(route:String?,tabRoute:String):Boolean{
    return tabRoute == HomeBottomBarItems.Market.destination
            && route == SEARCH_PAGE_ROUTE
}