package com.ethermail.androidchallenge.markets

import com.ethermail.androidchallenge.presentation.ui.UiText

sealed interface MarketScreenState {
    data class Success(val marketData: MarketUiItem) : MarketScreenState
    data object Loading : MarketScreenState
    data class Error(val error: UiText) : MarketScreenState
}