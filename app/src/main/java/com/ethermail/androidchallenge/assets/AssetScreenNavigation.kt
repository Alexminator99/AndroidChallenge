package com.ethermail.androidchallenge.assets

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ethermail.androidchallenge.markets.MarketScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object AssetScreenRoute

fun NavController.navigateToMarket(
    assetId: String,
    rank: String,
) {
    navigate(route = MarketScreenRoute(assetId, rank))
}

fun NavGraphBuilder.assetRoute(
    navigateToMarket: (String, String) -> Unit,
) {
    composable<AssetScreenRoute> {
        AssertScreenRoot(
            onNavigateToMarket = navigateToMarket,
        )
    }
}