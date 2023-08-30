package com.vferreirati.cryptocurrencyapp.domain.repository

import com.vferreirati.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.vferreirati.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}