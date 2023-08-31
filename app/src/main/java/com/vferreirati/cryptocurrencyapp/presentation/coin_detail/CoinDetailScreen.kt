package com.vferreirati.cryptocurrencyapp.presentation.coin_detail


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vferreirati.cryptocurrencyapp.data.remote.dto.TeamMember
import com.vferreirati.cryptocurrencyapp.domain.model.CoinDetail
import com.vferreirati.cryptocurrencyapp.presentation.coin_detail.components.CoinTag
import com.vferreirati.cryptocurrencyapp.presentation.coin_detail.components.TeamListItem
import com.vferreirati.cryptocurrencyapp.presentation.ui.theme.CryptoCurrencyAppTheme

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    CoinDetailScreenContent(
        state = state,
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreenContent(
    state: CoinDetailState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(8f),
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f),
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.bodyMedium,

                        )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag -> CoinTag(tag = tag) }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coin.team) { member ->
                    TeamListItem(
                        teamMember = member,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    )
                    Divider()
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CoinDetailScreenContentPreview() {
    CryptoCurrencyAppTheme {
        CoinDetailScreenContent(
            state = CoinDetailState(
                coin = CoinDetail(
                    name = "Test coin",
                    coinId = "btc",
                    description = "Test cooooin",
                    isActive = true,
                    rank = 1,
                    symbol = "btc",
                    tags = listOf("Proof of work", "Best coin"),
                    team = listOf(
                        TeamMember(
                            id = "",
                            name = "Satoshi Nakamoto",
                            position = "Founder"
                        ),
                        TeamMember(
                            id = "",
                            name = "vferreirati",
                            position = "Developer"
                        ),
                    )
                )
            )
        )
    }
}