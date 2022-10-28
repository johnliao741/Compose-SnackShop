package com.jazzhipster.snackshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jazzhipster.snackshop.presentation.home.HomePage
import com.jazzhipster.snackshop.presentation.login.EmailLoginPage
import com.jazzhipster.snackshop.presentation.login.MainLoginPage
import com.jazzhipster.snackshop.presentation.login.type.LoginType
import com.jazzhipster.snackshop.presentation.login.type.SocialLogin
import com.jazzhipster.snackshop.presentation.welcome.WelcomePage
object Routes{
    const val LOGIN_ROUTE = "login_route"
    const val HOME_ROUTE = "home_route"
}
object Destinations {
    //login_route
    const val WELCOME_PAGE_ROUTE = "welcome_page_route"
    const val MAIN_LOGIN_PAGE_ROUTE = "main_login_page_route"
    const val EMAIL_LOGIN_PAGE_ROUTE = "email_login_page_route"
    //home_route
    const val HOME_PAGE_ROUTE = "home_page_route"

}

@ExperimentalPagerApi
@Composable
fun NavGraph(
    startDestination:String = Routes.LOGIN_ROUTE
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        loginGraph(navController)
        homeGraph(navController)
    }
}

@ExperimentalPagerApi
fun NavGraphBuilder.loginGraph(navController: NavController){
    navigation(Destinations.WELCOME_PAGE_ROUTE, route = Routes.LOGIN_ROUTE){
        composable(Destinations.WELCOME_PAGE_ROUTE){
            WelcomePage(navAction = {
                navController.navigate(Destinations.MAIN_LOGIN_PAGE_ROUTE)
            })
        }
        composable(Destinations.MAIN_LOGIN_PAGE_ROUTE){
            MainLoginPage(navAction = { loginType ->
                when(loginType){
                    is LoginType.Email->{
                        navController.navigate(Destinations.EMAIL_LOGIN_PAGE_ROUTE)
                    }
                    is LoginType.Social->{
                        navController.navigate(Routes.HOME_ROUTE){
                            popUpTo(Routes.LOGIN_ROUTE) { inclusive = true }
                        }
                    }
                }
            })
        }
        composable(Destinations.EMAIL_LOGIN_PAGE_ROUTE){
            EmailLoginPage(navAction = {
                navController.navigate(Routes.HOME_ROUTE){
                    popUpTo(Routes.LOGIN_ROUTE) { inclusive = true }
                }
            })
        }

    }

}

fun NavGraphBuilder.homeGraph(navController: NavController){
    navigation(Destinations.HOME_PAGE_ROUTE, route = Routes.HOME_ROUTE){
        composable(Destinations.HOME_PAGE_ROUTE){
            HomePage(navAction = {

            })
        }
    }
}