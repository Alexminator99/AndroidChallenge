package com.ethermail.androidchallenge.markets


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ethermail.androidchallenge.presentation.components.ErrorBanner
import com.ethermail.androidchallenge.presentation.toFormattedDate

@Composable
fun MarketScreenRoot(
    marketViewModel: MarketViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val state by marketViewModel.state.collectAsStateWithLifecycle()

    return MarketScreen(state, onBack, onRetry = marketViewModel::retryRequest)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(
    state: MarketScreenState,
    onBack: () -> Unit,
    onRetry: () -> Unit
) = Box(modifier = Modifier.fillMaxSize()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Market", style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    Image(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(onClick = onBack)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state) {
                is MarketScreenState.Error -> {
                    ErrorBanner(
                        error = state.error.asString(),
                        onRetryClicked = onRetry
                    )
                }

                MarketScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is MarketScreenState.Success -> {
                    MarketItem(market = state.marketData)
                }
            }
        }
    }
}

@Composable
fun MarketItem(
    market: MarketUiItem,
) {
    Card(
        shape = RoundedCornerShape(10),
        modifier = Modifier.fillMaxSize(fraction = 0.9f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Exchange ID", fontWeight = FontWeight.Bold)
            Text(text = market.exchangeId)

            Text(text = "Rank", fontWeight = FontWeight.Bold)
            Text(text = market.rank)

            Text(text = "Price", fontWeight = FontWeight.Bold)
            Text(text = market.priceUsd)

            Text(text = "Updated", fontWeight = FontWeight.Bold)
            Text(text = market.updated.toFormattedDate())
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewMarketScreen() = MarketScreen(
    state = MarketScreenState.Success(
        MarketUiItem(
            exchangeId = "bibox",
            rank = "76",
            baseId = "bitcoin-sv",
            priceUsd = "80.3713214940290781",
            updated = 1711035429482,
        ),
    ),
    onBack = {},
    onRetry = {}
)