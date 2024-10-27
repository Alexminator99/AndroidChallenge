package com.ethermail.androidchallenge.data


import com.ethermail.androidchallenge.data.model.assets.AssetsApiData
import com.ethermail.androidchallenge.data.model.markets.MarketsApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val HOST_COINCAP = "https://api.coincap.io/"

interface CoincapService {

    @GET("/v2/assets")
    suspend fun getAssets(): Response<AssetsApiData>

    @GET("/v2/markets")
    suspend fun getMarkets(): Response<MarketsApiData>

    @GET("/v2/assets/{id}/markets")
    suspend fun getAssetHighestMarket(@Path("id") id: String): Response<MarketsApiData>

}