package com.ethermail.androidchallenge.data.model.markets

import com.ethermail.androidchallenge.domain.MarketInfo
import com.ethermail.androidchallenge.markets.MarketUiItem

fun MarketData?.toMarketInfo(timestamp: Long): MarketInfo {
    return when {
        this == null -> {
            MarketInfo(
                baseId = "",
                exchangeId = "",
                rank = "",
                priceUsd = "",
                updated = timestamp,
            )
        }

        else -> {
            MarketInfo(
                baseId = baseId ?: "",
                exchangeId = exchangeId ?: "",
                rank = "",
                priceUsd = priceUsd ?: "",
                updated = timestamp
            )
        }
    }
}

fun MarketInfo.toMarketUiItem(rank: String): MarketUiItem = MarketUiItem(
    baseId = baseId,
    exchangeId = exchangeId,
    rank = rank,
    priceUsd = priceUsd,
    updated = updated,
)