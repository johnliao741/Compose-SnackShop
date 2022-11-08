package com.jazzhipster.snackshop.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jazzhipster.snackshop.navigation.Destinations.SNACK_DETAIL_PAGE_ROUTE
import com.jazzhipster.snackshop.presentation.home.HomeDestination
import com.jazzhipster.snackshop.presentation.home.HomePage
import com.jazzhipster.snackshop.presentation.home.sub_page.SearchResultDetailPage
import com.jazzhipster.snackshop.presentation.login.EmailLoginPage
import com.jazzhipster.snackshop.presentation.login.MainLoginPage
import com.jazzhipster.snackshop.presentation.login.type.LoginType
import com.jazzhipster.snackshop.presentation.login.type.SocialLogin
import com.jazzhipster.snackshop.presentation.setting_account.SettingAccountPage1
import com.jazzhipster.snackshop.presentation.setting_account.SettingAccountPage2
import com.jazzhipster.snackshop.presentation.welcome.WelcomePage
import com.jazzhipster.snackshop.remote.model.SettingUserAccountRequest

object Routes {
    const val LOGIN_ROUTE = "login_route"
    const val HOME_ROUTE = "home_route"
    const val SETTING_USER_ACCOUNT = "setting_user_account"
}

object Destinations {
    //login_route
    const val WELCOME_PAGE_ROUTE = "welcome_page_route"
    const val MAIN_LOGIN_PAGE_ROUTE = "main_login_page_route"
    const val EMAIL_LOGIN_PAGE_ROUTE = "email_login_page_route"

    //home_route
    const val HOME_PAGE_ROUTE = "home_page_route"

    //snack_route
    const val SNACK_DETAIL_PAGE_ROUTE = "snack_detail_route"

    //setting_user_account
    const val SETTING_USER_ACCOUNT_1_ROUTE = "setting_user_account_1_route"
    const val SETTING_USER_ACCOUNT_2_ROUTE = "setting_user_account_2_route"

}

@ExperimentalLifecycleComposeApi
@ExperimentalPagerApi
@Composable
fun NavGraph(
    startDestination: String = Routes.LOGIN_ROUTE
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph(navController)
        homeGraph(navController)
        snackGraph(navController)
        settingUserAccountGraph(navController)
    }
}

@ExperimentalPagerApi
fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(Destinations.WELCOME_PAGE_ROUTE, route = Routes.LOGIN_ROUTE) {
        composable(Destinations.WELCOME_PAGE_ROUTE) {
            WelcomePage(navAction = {
                navController.navigate(Destinations.MAIN_LOGIN_PAGE_ROUTE)
            })
        }
        composable(Destinations.MAIN_LOGIN_PAGE_ROUTE) {
            MainLoginPage(navAction = { loginType ->
                when (loginType) {
                    is LoginType.Email -> {
                        navController.navigate(Destinations.EMAIL_LOGIN_PAGE_ROUTE)
                    }
                    is LoginType.Social -> {
                        navController.navigate(Routes.HOME_ROUTE) {
                            popUpTo(Routes.LOGIN_ROUTE) { inclusive = true }
                        }
                    }
                }
            })
        }
        composable(Destinations.EMAIL_LOGIN_PAGE_ROUTE) {
            EmailLoginPage(navAction = {
                navController.navigate(Routes.HOME_ROUTE) {
                    popUpTo(Routes.LOGIN_ROUTE) { inclusive = true }
                }
            })
        }

    }

}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(Destinations.HOME_PAGE_ROUTE, route = Routes.HOME_ROUTE) {
        composable(Destinations.HOME_PAGE_ROUTE) {
            HomePage(parentNavigation = navController)
        }
    }
}

@ExperimentalLifecycleComposeApi
fun NavGraphBuilder.snackGraph(navController: NavController) {
    composable("${SNACK_DETAIL_PAGE_ROUTE}/{snackId}") {
        val snackId = it.arguments?.getString("snackId") ?: ""
        SearchResultDetailPage(Modifier.fillMaxSize(), snackId,
            clickAction = { snackId ->
                navController.navigate("${SNACK_DETAIL_PAGE_ROUTE}/$snackId")
            }, backAction = {
                navController.popBackStack()
            })

    }
}

fun NavGraphBuilder.settingUserAccountGraph(navController: NavController) {
    navigation(Destinations.SETTING_USER_ACCOUNT_1_ROUTE, route = Routes.SETTING_USER_ACCOUNT) {
        composable(Destinations.SETTING_USER_ACCOUNT_1_ROUTE) {
            SettingAccountPage1() {
                navController.currentBackStackEntry?.arguments?.putSerializable("SettingUserAccount",it)
                navController.navigate(Destinations.SETTING_USER_ACCOUNT_2_ROUTE)
            }
        }
        composable(Destinations.SETTING_USER_ACCOUNT_2_ROUTE) {
            val settingUserAccount = navController.previousBackStackEntry?.arguments?.getSerializable("SettingUserAccount") as SettingUserAccountRequest
            SettingAccountPage2(
                settingUserAccount = settingUserAccount,
                navAction = {

                },
                backAction = {

                }
            )
        }
    }
}