package com.ethermail.androidchallenge.data.model.assets

import com.ethermail.androidchallenge.assets.AssetUiItem
import com.ethermail.androidchallenge.domain.AssetInfo

fun AssetData.toAssetInfo(): AssetInfo =
    AssetInfo(
        id = id,
        symbol = symbol,
        name = name,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        marketCapUsd = marketCapUsd,
        supply = supply,
        maxSupply = maxSupply,
        volumeUsd24Hr = volumeUsd24Hr,
        vwap24Hr = vwap24Hr,
        explorer = explorer,
        rank = rank,
    )

fun AssetsApiData.toListOfAssetInfo(): List<AssetInfo> =
    data?.map { it.toAssetInfo() } ?: emptyList()


// This can be a different approach depending on business requirements
fun AssetInfo.toAssetUiItem(): AssetUiItem = AssetUiItem(
    id = id ?: "",
    symbol = symbol ?: "",
    name = name ?: "",
    price = priceUsd ?: "",
    rank = rank ?: ""
)

fun List<AssetInfo>.toListOfAssetUiItem(): List<AssetUiItem> = map { it.toAssetUiItem() }