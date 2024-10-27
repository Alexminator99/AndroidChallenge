package com.ethermail.androidchallenge.data.model.assets

import com.ethermail.androidchallenge.data.CoincapService
import com.ethermail.androidchallenge.domain.AssetInfo
import com.ethermail.androidchallenge.domain.AssetRepository
import com.ethermail.androidchallenge.domain.util.DataError
import com.ethermail.androidchallenge.domain.util.Result
import com.ethermail.androidchallenge.domain.util.map
import com.ethermail.androidchallenge.networking.safeCall
import javax.inject.Inject

class AssetRepositoryImpl @Inject constructor(
    private val coincapService: CoincapService,
) : AssetRepository {
    override suspend fun getAssets(): Result<List<AssetInfo>, DataError.Network> =
        safeCall {
            coincapService.getAssets()
        }.map {
            it.toListOfAssetInfo()
        }
}