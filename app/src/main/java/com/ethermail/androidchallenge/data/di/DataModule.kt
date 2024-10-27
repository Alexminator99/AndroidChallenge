package com.ethermail.androidchallenge.data.di


import com.ethermail.androidchallenge.data.CoincapService
import com.ethermail.androidchallenge.data.HOST_COINCAP
import com.ethermail.androidchallenge.data.model.assets.AssetRepositoryImpl
import com.ethermail.androidchallenge.data.model.markets.MarketRepositoryImpl
import com.ethermail.androidchallenge.domain.AssetRepository
import com.ethermail.androidchallenge.domain.MarketRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideCoincapService(
        networkJson: Json
    ): CoincapService {
        return Retrofit.Builder()
            .baseUrl(HOST_COINCAP)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(CoincapService::class.java)
    }

    @Provides
    @Singleton
    fun provideAssetRepository(
        coincapService: CoincapService,
    ): AssetRepository {
        return AssetRepositoryImpl(coincapService)
    }

    @Provides
    @Singleton
    fun provideMarketRepository(
        coincapService: CoincapService,
    ): MarketRepository {
        return MarketRepositoryImpl(coincapService)
    }
}