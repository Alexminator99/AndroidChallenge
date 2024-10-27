package com.ethermail.androidchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ethermail.androidchallenge.assets.AssetScreenRoute
import com.ethermail.androidchallenge.assets.assetRoute
import com.ethermail.androidchallenge.assets.navigateToMarket
import com.ethermail.androidchallenge.markets.marketRoute

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AssetScreenRoute,
    ) {
        assetRoute(
            navigateToMarket = navController::navigateToMarket
        )

        marketRoute(
            onBack = navController::navigateUp
        )
    }
}