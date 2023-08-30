package com.vferreirati.cryptocurrencyapp.presentation.coin_detail

import com.vferreirati.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = "",
)
