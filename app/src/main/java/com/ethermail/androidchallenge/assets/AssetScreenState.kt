package com.ethermail.androidchallenge.assets

import com.ethermail.androidchallenge.presentation.ui.UiText

sealed interface AssetScreenState {
    data class Success(val assets: List<AssetUiItem>): AssetScreenState
    data object Loading : AssetScreenState
    data class Error(val error: UiText): AssetScreenState
}
