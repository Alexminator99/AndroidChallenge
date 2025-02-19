package com.ethermail.androidchallenge.data.model.assets

import kotlinx.serialization.Serializable

@Serializable
data class AssetData(
    val changePercent24Hr: String?,
    val explorer: String?,
    val id: String?,
    val marketCapUsd: String?,
    val maxSupply: String?,
    val name: String?,
    val priceUsd: String?,
    val rank: String?,
    val supply: String?,
    val symbol: String?,
    val volumeUsd24Hr: String?,
    val vwap24Hr: String?
)