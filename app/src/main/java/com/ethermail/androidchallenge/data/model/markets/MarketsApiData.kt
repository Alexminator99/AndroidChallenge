package com.ethermail.androidchallenge.data.model.markets

import kotlinx.serialization.Serializable

@Serializable
data class MarketsApiData(
    val data: List<MarketData>?,
    val timestamp: Long?
)