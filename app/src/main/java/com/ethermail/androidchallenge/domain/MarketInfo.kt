package com.ethermail.androidchallenge.domain

data class MarketInfo(
    val baseId: String,
    val exchangeId: String,
    val rank: String,
    val priceUsd: String,
    val updated: Long,
)
