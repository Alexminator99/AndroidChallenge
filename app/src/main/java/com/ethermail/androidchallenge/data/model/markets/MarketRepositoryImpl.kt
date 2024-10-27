package com.ethermail.androidchallenge.data.model.markets

import com.ethermail.androidchallenge.data.CoincapService
import com.ethermail.androidchallenge.domain.MarketInfo
import com.ethermail.androidchallenge.domain.MarketRepository
import com.ethermail.androidchallenge.domain.util.DataError
import com.ethermail.androidchallenge.domain.util.Result
import com.ethermail.androidchallenge.domain.util.map
import com.ethermail.androidchallenge.networking.safeCall
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val coincapService: CoincapService,
) : MarketRepository {
    override suspend fun getMarketData(baseId: String): Result<MarketInfo, DataError.Network> =
        safeCall {
            coincapService.getAssetHighestMarket(baseId)
        }.map { assetsApiData ->
            val timestamp = assetsApiData.timestamp ?: 1536343138230

            assetsApiData
                .data
                ?.maxByOrNull { it.volumeUsd24Hr?.toDouble() ?: 0.0 }
                .toMarketInfo(timestamp)
        }

}