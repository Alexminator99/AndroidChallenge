package com.ethermail.androidchallenge.assets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ethermail.androidchallenge.R
import com.ethermail.androidchallenge.presentation.theme.AndroidChallengeTheme
import com.ethermail.androidchallenge.presentation.ui.DevicePreviews
import com.ethermail.androidchallenge.presentation.ui.UiText

@Composable
fun AssertScreenRoot(
    onNavigateToMarket: (String, String) -> Unit,
    marketViewModel: AssetViewModel = hiltViewModel(),
) {
    val state by marketViewModel.state.collectAsStateWithLifecycle()

    return AssetsScreen(
        state,
        onElementClicked = onNavigateToMarket,
        onRetryClicked = marketViewModel::retryRequest
    )
}

@Composable
fun AssetsScreen(
    state: AssetScreenState,
    onElementClicked: (String, String) -> Unit,
    onRetryClicked: () -> Unit
) {
    when (state) {
        is AssetScreenState.Error -> {
            // This UI is just demonstrative and can be improved
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.errorContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.error,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.error.asString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Button(
                    onClick = onRetryClicked,
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                    )
                ) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }

        AssetScreenState.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        is AssetScreenState.Success -> {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(top = 4.dp),
            ) {
                items(
                    count = state.assets.size,
                    key = { index -> state.assets[index].symbol }) { index ->
                    AssetView(asset = state.assets[index], onClick = onElementClicked)
                }
            }
        }
    }


}

@Composable
private fun AssetView(asset: AssetUiItem, onClick: (String, String) -> Unit) = Card(
    shape = RoundedCornerShape(10),
    modifier = Modifier.fillMaxWidth(),
) {
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable { onClick(asset.id, asset.rank) }) {
        Text(text = asset.name)
        Row {
            Text(text = asset.symbol, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = asset.price)
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewAssetView() {
    AndroidChallengeTheme {
        AssetsScreen(
            AssetScreenState.Success(dummyAssets),
            onElementClicked = { _, _ -> },
            onRetryClicked = {}
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewLoading() {
    AndroidChallengeTheme {
        AssetsScreen(
            AssetScreenState.Loading,
            onElementClicked = { _, _ -> },
            onRetryClicked = {}
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewError() {
    AndroidChallengeTheme {
        AssetsScreen(
            AssetScreenState.Error(UiText.DynamicString("Error")),
            onElementClicked = { _, _ -> },
            onRetryClicked = {}
        )
    }
}