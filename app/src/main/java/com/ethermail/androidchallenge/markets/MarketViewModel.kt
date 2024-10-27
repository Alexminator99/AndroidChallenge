package com.ethermail.androidchallenge.markets

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ethermail.androidchallenge.data.model.markets.toMarketUiItem
import com.ethermail.androidchallenge.domain.MarketRepository
import com.ethermail.androidchallenge.domain.util.Result
import com.ethermail.androidchallenge.presentation.ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val marketRepository: MarketRepository,
) : ViewModel() {
    val state = MutableStateFlow<MarketScreenState>(MarketScreenState.Loading)
    private val baseId = savedStateHandle.toRoute<MarketScreenRoute>().assetBaseId
    private val rank = savedStateHandle.toRoute<MarketScreenRoute>().rank

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchHighestMarket()
        }
    }

    fun retryRequest() {
        viewModelScope.launch(Dispatchers.IO) { fetchHighestMarket() }
    }

    private suspend fun fetchHighestMarket() {
        state.update { MarketScreenState.Loading }
        when (val result = marketRepository.getMarketData(baseId = baseId)) {
            is Result.Error -> {
                state.update {
                    MarketScreenState.Error(result.error.asUiText())
                }
            }

            is Result.Success -> {
                state.update {
                    MarketScreenState.Success(result.data.toMarketUiItem(rank))
                }
            }
        }
    }


}