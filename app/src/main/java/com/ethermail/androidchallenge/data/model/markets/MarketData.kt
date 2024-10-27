package com.ethermail.androidchallenge.data.model.markets

import kotlinx.serialization.Serializable

@Serializable
data class MarketData(
    val baseId: String?,
    val baseSymbol: String?,
    val exchangeId: String?,
    val priceUsd: String?,
    val quoteId: String?,
    val quoteSymbol: String?,
    val volumeUsd24Hr: String?
)