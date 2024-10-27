package com.ethermail.androidchallenge.markets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class MarketScreenRoute(val assetBaseId: String, val rank: String)

fun NavGraphBuilder.marketRoute(
    onBack: () -> Unit,
) {
    composable<MarketScreenRoute> {
        MarketScreenRoot(
            onBack = onBack
        )
    }
}