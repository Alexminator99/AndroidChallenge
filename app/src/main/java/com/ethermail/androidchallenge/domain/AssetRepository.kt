package com.ethermail.androidchallenge.domain

import com.ethermail.androidchallenge.domain.util.DataError
import com.ethermail.androidchallenge.domain.util.Result

interface AssetRepository {
    suspend fun getAssets(): Result<List<AssetInfo>, DataError.Network>
}