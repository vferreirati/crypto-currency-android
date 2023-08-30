package com.vferreirati.cryptocurrencyapp.data.repository

import com.vferreirati.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.vferreirati.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.vferreirati.cryptocurrencyapp.data.remote.dto.CoinDto
import com.vferreirati.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoin(coinId)
    }
}