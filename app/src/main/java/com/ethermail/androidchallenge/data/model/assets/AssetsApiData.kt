package com.ethermail.androidchallenge.data.model.assets

import kotlinx.serialization.Serializable

@Serializable
data class AssetsApiData(
    val data: List<AssetData>?,
    val timestamp: Long?
)
