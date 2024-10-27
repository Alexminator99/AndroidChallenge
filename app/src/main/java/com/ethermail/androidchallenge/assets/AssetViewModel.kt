package com.ethermail.androidchallenge.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethermail.androidchallenge.data.model.assets.toListOfAssetUiItem
import com.ethermail.androidchallenge.domain.AssetRepository
import com.ethermail.androidchallenge.domain.util.Result
import com.ethermail.androidchallenge.presentation.ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    private val assetRepository: AssetRepository,
) : ViewModel() {
    val state = MutableStateFlow<AssetScreenState>(AssetScreenState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAssets()
        }
    }

    fun retryRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAssets()
        }
    }

    private suspend fun fetchAssets() {
        when (val result = assetRepository.getAssets()) {
            is Result.Error -> {
                state.update {
                    AssetScreenState.Error(result.error.asUiText())
                }
            }

            is Result.Success -> {
                state.update {
                    AssetScreenState.Success(result.data.toListOfAssetUiItem())
                }
            }
        }
    }
}