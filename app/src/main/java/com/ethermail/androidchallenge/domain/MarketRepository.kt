package com.ethermail.androidchallenge.domain

import com.ethermail.androidchallenge.domain.util.DataError
import com.ethermail.androidchallenge.domain.util.Result

interface MarketRepository {
    suspend fun getMarketData(baseId: String): Result<MarketInfo, DataError.Network>
}