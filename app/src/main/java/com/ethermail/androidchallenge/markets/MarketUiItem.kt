package com.ethermail.androidchallenge.markets

data class MarketUiItem(
    val baseId: String,
    val exchangeId: String,
    val rank: String,
    val priceUsd: String,
    val updated: Long,
)